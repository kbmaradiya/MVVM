package com.example.mit.mvvmdemo.viewmodels;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.example.mit.mvvmdemo.models.ProductModel;
import com.example.mit.mvvmdemo.repositeries.ProductRepository;

import java.util.ArrayList;

public class ProductListViewModel extends ViewModel {

    private static ProductListViewModel mProductListViewModel;
    MutableLiveData<ArrayList<ProductModel>> productMutableLiveData;
    MutableLiveData<String> activity;
    ProductRepository productRepository;

    public static int count = 1;

    public MutableLiveData<String> getActivity() {
        if (activity==null){
            activity=new MutableLiveData<>();
        }
        return activity;
    }

    public MutableLiveData<ArrayList<ProductModel>> getProductMutableLiveData() {
        if (productMutableLiveData == null) {
            productMutableLiveData = new MutableLiveData<>();
        }
        return productMutableLiveData;
    }


    public void init() {
        productRepository = ProductRepository.getinstance();

        if (productMutableLiveData != null) {
            return;
        }
        productMutableLiveData = productRepository.getProductList();

    }

    public static ProductListViewModel getInstance(FragmentActivity activity){
        if (mProductListViewModel==null) {
            mProductListViewModel = ViewModelProviders.of(activity).get(ProductListViewModel.class);
        }
        return mProductListViewModel;
    }
}
