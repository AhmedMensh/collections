package com.android.dev.ahmed.collections.ui.activties.collection;

import android.util.Log;

import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.NewArrival;
import com.android.dev.ahmed.collections.network.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionPresenter {

    private static final String TAG = "CollectionPresenter";
    private CollectionViewInf viewInf;

    public CollectionPresenter(CollectionViewInf viewInf) {

        this.viewInf = viewInf;
    }



    public void getNewArrivals(String lang , int currencyId){


        Service.Fetcher.getInstance().getNewArrivals(lang,currencyId).enqueue(new Callback<ApiResponse<List<NewArrival>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<NewArrival>>> call, Response<ApiResponse<List<NewArrival>>> response) {

                try {
                    viewInf.displayData((response.body().getData()));
                }catch (Exception e){
                    Log.e(TAG, "onResponse: "+e.getLocalizedMessage() );
                }


            }

            @Override
            public void onFailure(Call<ApiResponse<List<NewArrival>>> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());

            }
        });
    }
    public void getProductsByCategory(int branchId){

        Service.Fetcher.getInstance().getProductsByCategory(branchId ,"en").enqueue(new Callback<ApiResponse<List<NewArrival>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<NewArrival>>> call, Response<ApiResponse<List<NewArrival>>> response) {

                try{
                    viewInf.displayData(response.body().getData());
                }catch (Exception e){
                    Log.e(TAG, "onResponse: "+e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<NewArrival>>> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }
}
