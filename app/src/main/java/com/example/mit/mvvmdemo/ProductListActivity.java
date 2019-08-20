package com.example.mit.mvvmdemo;

import android.Manifest;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;


import com.example.mit.mvvmdemo.adapter.ProductListAdapter;
import com.example.mit.mvvmdemo.models.ProductModel;
import com.example.mit.mvvmdemo.viewmodels.ProductListViewModel;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;

public class ProductListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton fbtnCart;
    private ProductListAdapter mAdapter;

    private ProductListViewModel mProductListViewModel;

    private FusedLocationProviderClient fusedLocationClient;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list);

        init();


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void init() {

        recyclerView = findViewById(R.id.recyclerView);

        Log.e("ProductListActivity"," init : "+ProductListViewModel.count);

        initViewModelData();

    }


    private void initViewModelData() {
        mProductListViewModel =ProductListViewModel.getInstance(this);

        mProductListViewModel.init();

        mProductListViewModel.getProductMutableLiveData().observe(this, new Observer<ArrayList<ProductModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<ProductModel> productModels) {

                mAdapter.notifyDataSetChanged();


            }
        });

        mProductListViewModel.getActivity().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Log.e("ProductListActivity","activity name: "+s);
            }
        });


        initRecyclerView();

    }


    private void initRecyclerView() {
        mAdapter = new ProductListAdapter(this, mProductListViewModel.getProductMutableLiveData().getValue(), mProductListViewModel);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(mAdapter);
        mProductListViewModel.getActivity().setValue("ProductListActivity");
    }


}
