package com.android.collections.ui.activties.wish_list;

import android.content.Context;
import android.util.Log;

import com.android.collections.helpers.Constants;
import com.android.collections.helpers.PublicViewInf;
import com.android.collections.helpers.SharedPreferencesManager;
import com.android.collections.models.ApiResponse;
import com.android.collections.models.Favorite;
import com.android.collections.network.Service;

import java.util.List;
import java.util.PrimitiveIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WishListPresenter {

    private static final String TAG = "WishListPresenter";
    private PublicViewInf publicViewInf;
    private WishListViewInf wishListViewInf;


    public WishListPresenter(PublicViewInf publicViewInf, WishListViewInf wishListViewInf) {
        this.publicViewInf = publicViewInf;
        this.wishListViewInf = wishListViewInf;

    }

    public void getWishList(int userId){

        Service.Fetcher.getInstance().getFavoriteList(userId,"en").enqueue(new Callback<ApiResponse<List<Favorite>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Favorite>>> call, Response<ApiResponse<List<Favorite>>> response) {

                if (response.body().getSuccess()){
                    wishListViewInf.displayWishList(response.body().getData());
                }else {
                    publicViewInf.showMessage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Favorite>>> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
            }
        });
    }

    public void removeFromFavorite(int productId, int userId){

        Service.Fetcher.getInstance().addAndDeleteFromFavorite(productId,userId,"dislike").enqueue(new Callback<ApiResponse>() {
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
