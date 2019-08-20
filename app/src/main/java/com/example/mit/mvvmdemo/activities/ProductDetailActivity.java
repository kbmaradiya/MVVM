package com.example.mit.mvvmdemo.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mit.mvvmdemo.R;
import com.example.mit.mvvmdemo.viewmodels.ProductListViewModel;

public class ProductDetailActivity extends AppCompatActivity {

    private ProductListViewModel mProductListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        mProductListViewModel = ProductListViewModel.getInstance(this);

        mProductListViewModel.getActivity().setValue("ProductDetailActivity");
    }
}
