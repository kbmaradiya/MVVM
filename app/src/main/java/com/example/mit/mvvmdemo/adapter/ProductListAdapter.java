package com.example.mit.mvvmdemo.adapter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.mit.mvvmdemo.R;
import com.example.mit.mvvmdemo.activities.ProductDetailActivity;
import com.example.mit.mvvmdemo.models.ProductModel;
import com.example.mit.mvvmdemo.viewmodels.ProductListViewModel;

import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {


    private FragmentActivity mActivity;
    private ArrayList<ProductModel> mProductModelArrayList;
    private ProductListViewModel mProductListViewModel;

    public ProductListAdapter(FragmentActivity activity, ArrayList<ProductModel> value, ProductListViewModel productListViewModel) {

        this.mActivity=activity;
        this.mProductListViewModel=productListViewModel;
        this.mProductModelArrayList=value;

//         mProductListViewModel= ViewModelProviders.of(mActivity).get(ProductListViewModel.class);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View  view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_product_list,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int pos) {


        holder.txtProductName.setText(mProductModelArrayList.get(pos).getName());

        holder.txtProductPrice.setText(mProductModelArrayList.get(pos).getPrice()+"");

        holder.txtQuantity.setText(mProductModelArrayList.get(pos).getQuantity()+"");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mActivity, ProductDetailActivity.class);
                mActivity.startActivity(intent);
            }
        });

        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mProductModelArrayList.get(pos).setQuantity(Integer.parseInt(holder.txtQuantity.getText().toString())+1);
                mProductModelArrayList.set(pos, mProductModelArrayList.get(pos));

                mProductListViewModel.getProductMutableLiveData()
                        .setValue(mProductModelArrayList);

                ProductListViewModel.count++;
            }
        });

        holder.imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                                if (Integer.parseInt(holder.txtQuantity.getText().toString())>0)
                                {
                                    mProductModelArrayList.get(pos).setQuantity(Integer.parseInt(holder.txtQuantity.getText().toString())-1);
                                    mProductModelArrayList.set(pos, mProductModelArrayList.get(pos));
                                    mProductListViewModel.getProductMutableLiveData()
                                            .setValue(mProductModelArrayList);
                                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mProductModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtProductName;
        TextView txtProductPrice;
        TextView txtQuantity;
        ImageView imgAdd;
        ImageView imgMinus;
        Button btnAdd;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtProductName=itemView.findViewById(R.id.txtProductName);
            txtProductPrice=itemView.findViewById(R.id.txtProductPrice);
            txtQuantity =itemView.findViewById(R.id.txtQuantity);
            imgAdd=itemView.findViewById(R.id.imgAdd);
            imgMinus=itemView.findViewById(R.id.imgMinus);
            btnAdd=itemView.findViewById(R.id.btnAdd);

        }
    }
}
