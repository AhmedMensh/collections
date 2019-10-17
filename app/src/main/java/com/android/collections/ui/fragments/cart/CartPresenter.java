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
    private Context context;

    public CartPresenter(PublicViewInf publicViewInf, CartViewInf viewInf ,Context context) {
        this.publicViewInf = publicViewInf;
        this.viewInf = viewInf;
        this.context = context;
    }


    public void getCartItems(){

        Service.Fetcher.getInstance().getCartItems(2,"en",2).enqueue(new Callback<ApiResponse<List<CartItem>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<CartItem>>> call, Response<ApiResponse<List<CartItem>>> response) {

                if (response.body().getSuccess()){
                    viewInf.displayCartItems(response.body());
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


    public void getProductsLikeMe(){
        Service.Fetcher.getInstance().getProductsLikeMe("en",
                SharedPreferencesManager.getIntValue(context, Constants.USER_ID),1).enqueue(new Callback<ApiResponse<List<TopOffer>>>() {
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
}
