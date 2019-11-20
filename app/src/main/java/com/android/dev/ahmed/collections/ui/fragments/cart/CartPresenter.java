package com.android.dev.ahmed.collections.ui.fragments.cart;

import android.content.Context;
import android.util.Log;

import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;
import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.CartItems;
import com.android.dev.ahmed.collections.models.PaymentResponse;
import com.android.dev.ahmed.collections.network.Service;

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

        Service.Fetcher.getInstance().getCartItems("en",
                SharedPreferencesManager.getStringValue(context, Constants.MAC_ADDRESS),1)
                .enqueue(new Callback<CartItems>() {
            @Override
            public void onResponse(Call<CartItems> call, Response<CartItems> response) {


                if (response.body().getStatus()){
                    Log.e(TAG, "onResponse: ");
                    viewInf.displayCartItems(response.body());
                }else {
                    Log.e(TAG, "onResponse: else" );
                    publicViewInf.showMessage(response.message());
                }
            }

            @Override
            public void onFailure(Call<CartItems> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
            }
        });
    }



    public void removeItemFromCart(int cartId) {


        Service.Fetcher.getInstance().deleteFromCart(cartId).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                try {
                    if (response.body().getSuccess()){
                        getCartItems();
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

    public void updateItemQuantity( int productId ,int quantity,int sizeId ,int colorId){

        Service.Fetcher.getInstance().updateItemQuantity(productId,quantity,sizeId,colorId).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                try{


                    publicViewInf.showMessage(response.body().getMessage());
                    if (response.body().getSuccess()){

                        getCartItems();
                    }
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

    public void paymentCheckout(int paymentType,int promoCode){
        Service.Fetcher.getInstance().paymentCheckout(paymentType,"en",true,promoCode,1)
                .enqueue(new Callback<ApiResponse<PaymentResponse>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<PaymentResponse>> call, Response<ApiResponse<PaymentResponse>> response) {

                        try{
                            if (!response.body().getMessage().equals("")){
                                viewInf.checkoutResponse(response.body());
                                publicViewInf.showMessage(response.body().getMessage());
                        }
                        }catch (Exception e){

                            Log.e(TAG, "onFailure: "+e.getLocalizedMessage());
                        }

                    }

                    @Override
                    public void onFailure(Call<ApiResponse<PaymentResponse>> call, Throwable t) {


                        Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
                    }
                });
    }

}
