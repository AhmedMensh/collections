package com.android.dev.ahmed.collections.ui.activties.wish_list;

import android.util.Log;

import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.Favorite;
import com.android.dev.ahmed.collections.network.Service;

import java.util.List;

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

    public void getWishList(){

        Service.Fetcher.getInstance().getFavoriteList("en").enqueue(new Callback<ApiResponse<List<Favorite>>>() {
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

    public void removeFromFavorite(int productId){

        Service.Fetcher.getInstance().addAndDeleteFromFavorite(productId,"dislike").enqueue(new Callback<ApiResponse>() {
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
