package com.android.collections.ui.activties.product_details;

import android.content.Context;
import android.util.Log;

import com.android.collections.R;
import com.android.collections.helpers.Constants;
import com.android.collections.helpers.PublicViewInf;
import com.android.collections.helpers.SharedPreferencesManager;
import com.android.collections.models.ApiResponse;
import com.android.collections.models.product_detalis.ProductDetails;
import com.android.collections.network.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsPresenter {

    private static final String TAG = "ProductDetailsPresenter";
    private PublicViewInf publicViewInf;
    private ProductDetailsViewInf viewInf;


    public ProductDetailsPresenter(PublicViewInf publicViewInf, ProductDetailsViewInf viewInf) {
        this.publicViewInf = publicViewInf;
        this.viewInf = viewInf;

    }


    public void addToCart(int userId ,int productId  ,int quantity ,int sizeId ,int colorId){

        Service.Fetcher.getInstance().addToCart(productId, userId,quantity,sizeId,colorId)
                .enqueue(new Callback<ApiResponse>() {
                    @Override
                    public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                        try {
                            publicViewInf.showMessage(response.body().getMessage());
                        }catch (Exception e){
                            Log.e(TAG, "onResponse: "+e.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse> call, Throwable t) {

                        Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
                    }
                });
    }
    public void getProductDetails(int productId ,int userId){
        Service.Fetcher.getInstance().getProductDetails(productId, userId,"en").enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {

                if (response.body().getStatus()){
                    viewInf.displayProductDetails(response.body());
                }else {
                    publicViewInf.showMessage("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
                publicViewInf.showMessage("Something went wrong");
            }
        });
    }

    public void addToFavorite(int productId,int userId){

        Service.Fetcher.getInstance().addAndDeleteFromFavorite(productId,userId,"like").enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                try {
                    publicViewInf.showMessage(response.body().getMessage());
                }catch (Exception e){
                    Log.e(TAG, "onResponse: "+e.getLocalizedMessage() );
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                publicViewInf.showMessage("Something went wrong");
            }
        });
    }
}
