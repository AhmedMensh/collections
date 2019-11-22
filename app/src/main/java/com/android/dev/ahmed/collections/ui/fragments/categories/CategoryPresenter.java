package com.android.dev.ahmed.collections.ui.fragments.categories;

import android.util.Log;

import com.android.dev.ahmed.collections.CollectionApp;
import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.Category;
import com.android.dev.ahmed.collections.network.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {

    private static final String TAG = "CategoryPresenter";
    private PublicViewInf publicViewInf;
    private CategoryViewInf categoryViewInf;

    public CategoryPresenter(PublicViewInf publicViewInf, CategoryViewInf categoryViewInf) {
        this.publicViewInf = publicViewInf;
        this.categoryViewInf = categoryViewInf;
    }

    public void getMainCategories(){

        Service.Fetcher.getInstance().getMainCategories(CollectionApp.getLanguage(),CollectionApp.getUserId()).enqueue(new Callback<ApiResponse<List<Category>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Category>>> call, Response<ApiResponse<List<Category>>> response) {
                if (response.body().getSuccess()){

                    categoryViewInf.displaycategoryList(response.body().getData());
                    for (int i = 0; i <response.body().getData().size() ; i++) {

                        Log.e(TAG, "onResponse: "+response.body().getData().get(i).getSubCategories().get(0) );
                    }
                }else {
                    publicViewInf.showMessage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Category>>> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }
}
