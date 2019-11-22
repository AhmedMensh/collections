package com.android.dev.ahmed.collections.ui.activties.product_details;

import android.util.Log;

import com.android.dev.ahmed.collections.CollectionApp;
import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.ProductDetails;
import com.android.dev.ahmed.collections.network.Service;

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


    public void addToCart(int productId  ,int quantity ,int sizeId ,int colorId){

        Service.Fetcher.getInstance().addToCart(productId, CollectionApp.getUserId(),quantity,sizeId,colorId,
                CollectionApp.isIsRegisterd(),CollectionApp.getMacAddress())
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
    public void getProductDetails(int productId){
        Service.Fetcher.getInstance().getProductDetails(productId,CollectionApp.getUserId(),CollectionApp.getLanguage()).enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {

                try{
                    viewInf.displayProductDetails(response.body());
                    Log.e(TAG, "onResponse: "+response.body().getCatName());
                }catch (Exception e){
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

    public void addToFavorite(int productId){

        Service.Fetcher.getInstance().addAndDeleteFromFavorite(productId,CollectionApp.getUserId(),"like").enqueue(new Callback<ApiResponse>() {
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
