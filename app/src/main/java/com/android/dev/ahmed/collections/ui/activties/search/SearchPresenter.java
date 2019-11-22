package com.android.dev.ahmed.collections.ui.activties.search;

import android.util.Log;

import com.android.dev.ahmed.collections.CollectionApp;
import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.TopOffer;
import com.android.dev.ahmed.collections.network.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter {

    private static final String TAG = "SearchPresenter";
    private PublicViewInf publicViewInf;
    private SearchViewInf viewInf;

    public SearchPresenter(PublicViewInf publicViewInf, SearchViewInf viewInf) {
        this.publicViewInf = publicViewInf;
        this.viewInf = viewInf;
    }

    public void getSearchResult(String productName){
        Service.Fetcher.getInstance().searchInProducts(productName, CollectionApp.getUserId(),
                CollectionApp.getLanguage(),2).enqueue(new Callback<ApiResponse<List<TopOffer>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<TopOffer>>> call, Response<ApiResponse<List<TopOffer>>> response) {

                try {
                    if (response.body().getSuccess()){
                        viewInf.dispalySearchResult(response.body().getData());
                    }
                    else {
                        publicViewInf.showMessage(response.body().getMessage());
                    }
                }catch (Exception e){
                    Log.e(TAG, "onResponse: "+e.getLocalizedMessage() );
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<TopOffer>>> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
            }
        });
    }
}
