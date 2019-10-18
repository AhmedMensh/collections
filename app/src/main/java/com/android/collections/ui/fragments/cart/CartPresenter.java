package com.android.collections.ui.fragments.cart;

import android.content.Context;
import android.util.Log;

import com.android.collections.helpers.Constants;
import com.android.collections.helpers.PublicViewInf;
import com.android.collections.helpers.SharedPreferencesManager;
import com.android.collections.models.ApiResponse;
import com.android.collections.models.CartItem;
import com.android.collections.models.TopOffer;
import com.android.collections.network.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartPresenter {

    private static final String TAG = "CartPresenter";
    private PublicViewInf publicViewInf;
    private CartViewInf viewInf;


    public CartPresenter(PublicViewInf publicViewInf, CartViewInf viewInf) {
        this.publicViewInf = publicViewInf;
        this.viewInf = viewInf;

    }


    public void getCartItems(int userId){

        Service.Fetcher.getInstance().getCartItems(userId,"en",2).enqueue(new Callback<ApiResponse<List<CartItem>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<CartItem>>> call, Response<ApiResponse<List<CartItem>>> response) {

                Log.e(TAG, "onResponse: "+response.message());
                if (response.body().getSuccess()){
                    viewInf.displayCartItems(response.body());
                    Log.e(TAG, "onResponse: "+response.body().getSuccess());
                }else {
                    publicViewInf.showMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<CartItem>>> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
            }
        });
    }


    public void getProductsLikeMe(int userId){
        Service.Fetcher.getInstance().getProductsLikeMe("en", userId,1).enqueue(new Callback<ApiResponse<List<TopOffer>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<TopOffer>>> call, Response<ApiResponse<List<TopOffer>>> response) {

                try {
                    viewInf.displayProductsLikeMe(response.body().getData());

                }catch (Exception e){
                    Log.e(TAG, "onResponse: "+e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<TopOffer>>> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
            }
        });
    }

    public void removeItemFromCart(int cartId ,int userId) {


        Service.Fetcher.getInstance().deleteFromCart(cartId).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                try {
                    if (response.body().getSuccess()){
                        getCartItems(userId);
                    }
                    publicViewInf.showMessage(response.body().getMessage());
                }catch (Exception e){
                    Log.e(TAG, "onResponse: "+e.getLocalizedMessage() );
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }
}
