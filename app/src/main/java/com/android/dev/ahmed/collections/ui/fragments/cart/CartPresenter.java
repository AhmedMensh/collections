package com.android.dev.ahmed.collections.ui.fragments.cart;

import android.util.Log;

import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.CartItem;
import com.android.dev.ahmed.collections.models.PaymentResponse;
import com.android.dev.ahmed.collections.models.TopOffer;
import com.android.dev.ahmed.collections.network.Service;

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

    public void updateItemQuantity(int userId , int productId ,int quantity,int sizeId ,int colorId){

        Service.Fetcher.getInstance().updateItemQuantity(userId,productId,quantity,sizeId,colorId).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {

                try{


                    publicViewInf.showMessage(response.body().getMessage());
                    if (response.body().getSuccess()){

                        getCartItems(userId);
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

    public void paymentCheckout(int userId , int paymentType,int promoCode){
        Service.Fetcher.getInstance().paymentCheckout(userId,paymentType,"en",true,promoCode,1)
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
