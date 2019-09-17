package com.android.collections.ui.fragments.categories;

import android.content.Context;
import android.util.Log;

import com.android.collections.R;
import com.android.collections.helpers.PublicViewInf;
import com.android.collections.models.ApiResponse;
import com.android.collections.models.Category;
import com.android.collections.network.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryPresenter {

    private static final String TAG = "CategoryPresenter";
    private Context context;
    private PublicViewInf publicViewInf;
    private CategoryViewInf categoryViewInf;

    public CategoryPresenter(Context context, PublicViewInf publicViewInf, CategoryViewInf categoryViewInf) {
        this.context = context;
        this.publicViewInf = publicViewInf;
        this.categoryViewInf = categoryViewInf;
    }

    public void getMainCategories(){

        Service.Fetcher.getInstance().getMainCategories("en").enqueue(new Callback<ApiResponse<List<Category>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Category>>> call, Response<ApiResponse<List<Category>>> response) {
                if (response.body().getSuccess()){

                    categoryViewInf.displaycategoryList(response.body().getData());
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