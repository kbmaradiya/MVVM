package com.example.mit.mvvmdemo;

import android.arch.lifecycle.LiveData;

import java.util.List;

public class ProductQuantityLiveData extends LiveData<List<ProductQuantityLiveData>> {

    @Override
    protected void onActive() {
        super.onActive();
    }

    @Override
    protected void onInactive() {
        super.onInactive();
    }


}

