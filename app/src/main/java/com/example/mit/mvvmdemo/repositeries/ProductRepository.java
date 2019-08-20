package com.example.mit.mvvmdemo.repositeries;

import android.arch.lifecycle.MutableLiveData;

import com.example.mit.mvvmdemo.models.ProductModel;

import java.util.ArrayList;

public class ProductRepository {

    MutableLiveData<ArrayList<ProductModel>> productModelLiveData;
    private static ProductRepository productRepository;

    public static ProductRepository getinstance(){
        if (productRepository==null){
            productRepository=new ProductRepository();
        }
        return productRepository;
    }


    public MutableLiveData<ArrayList<ProductModel>> getProductList(){
        ArrayList<ProductModel> productModels=new ArrayList<>();
        productModelLiveData = new MutableLiveData<>();

        productModels.add(new ProductModel(1, "ProOne",10,0));
        productModels.add(new ProductModel(2, "ProTwo",20,0));
        productModels.add(new ProductModel(3, "ProThree",10,0));
        productModels.add(new ProductModel(4, "ProFour",30,0));
        productModels.add(new ProductModel(5, "ProFive",50,0));

        productModelLiveData.setValue(productModels);

        return productModelLiveData;
    }
}
