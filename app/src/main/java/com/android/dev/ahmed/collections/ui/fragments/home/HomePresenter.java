package com.android.dev.ahmed.collections.ui.fragments.home;

import android.util.Log;

import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.FlashSale;
import com.android.dev.ahmed.collections.models.NewArrival;
import com.android.dev.ahmed.collections.models.NewTrend;
import com.android.dev.ahmed.collections.models.Slider;
import com.android.dev.ahmed.collections.models.TopOffer;
import com.android.dev.ahmed.collections.network.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter {

    private static final String TAG = "HomePresenter";
    private PublicViewInf publicViewInf;
    private HomeViewInf homeViewInf;

    public HomePresenter(PublicViewInf publicViewInf, HomeViewInf homeViewInf) {
        this.publicViewInf = publicViewInf;
        this.homeViewInf = homeViewInf;
    }

    public void getTopOffers(int userId , String lang , int currencyId){

        publicViewInf.showProgressBar();
        Service.Fetcher.getInstance().getTopOffers(userId,lang,currencyId).enqueue(new Callback<ApiResponse<List<TopOffer>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<TopOffer>>> call, Response<ApiResponse<List<TopOffer>>> response) {

                publicViewInf.hideProgressBar();

                if (response.body().getSuccess()){

                    homeViewInf.displayTopOffers(response.body().getData());
                }else {
                    publicViewInf.showMessage(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ApiResponse<List<TopOffer>>> call, Throwable t) {

                publicViewInf.hideProgressBar();
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());

            }
        });
    }


    public void getFlashSale(int userId , String lang , int currencyId){

        publicViewInf.showProgressBar();
        Service.Fetcher.getInstance().getFlashSale(userId,lang,currencyId).enqueue(new Callback<ApiResponse<List<FlashSale>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<FlashSale>>> call, Response<ApiResponse<List<FlashSale>>> response) {

                publicViewInf.hideProgressBar();

                if (response.body().getSuccess()){

                    homeViewInf.displayFlashSale(response.body().getData());
                }else {
                    publicViewInf.showMessage(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ApiResponse<List<FlashSale>>> call, Throwable t) {

                publicViewInf.hideProgressBar();
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());

            }
        });
    }


    public void getNewTrends(int userId , String lang , int currencyId){

        publicViewInf.showProgressBar();
        Service.Fetcher.getInstance().getNewTrends(userId,lang,currencyId).enqueue(new Callback<ApiResponse<List<NewTrend>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<NewTrend>>> call, Response<ApiResponse<List<NewTrend>>> response) {

                publicViewInf.hideProgressBar();

                if (response.body().getSuccess()){

                    homeViewInf.displayNewTrends(response.body().getData());
                }else {
                    publicViewInf.showMessage(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ApiResponse<List<NewTrend>>> call, Throwable t) {

                publicViewInf.hideProgressBar();
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());

            }
        });
    }

    public void getNewArrivals(int userId , String lang , int currencyId){

        publicViewInf.showProgressBar();
        Service.Fetcher.getInstance().getNewArrivals(userId,lang,currencyId).enqueue(new Callback<ApiResponse<List<NewArrival>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<NewArrival>>> call, Response<ApiResponse<List<NewArrival>>> response) {

                publicViewInf.hideProgressBar();

                if (response.body().getSuccess()){

                    homeViewInf.displayNewArrivals(response.body().getData());
                }else {
                    publicViewInf.showMessage(response.body().getMessage());
                }

            }

            @Override
            public void onFailure(Call<ApiResponse<List<NewArrival>>> call, Throwable t) {

                publicViewInf.hideProgressBar();
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());

            }
        });
    }

    public void getSliderImages(){
        Service.Fetcher.getInstance().getSliderImages("en","home",0).enqueue(new Callback<ApiResponse<List<Slider>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Slider>>> call, Response<ApiResponse<List<Slider>>> response) {
                try{

                    homeViewInf.displaySliderImages(response.body().getData());
                }catch (Exception e){
                    Log.e(TAG, "onResponse: "+e.getLocalizedMessage() );
                }

            }

            @Override
            public void onFailure(Call<ApiResponse<List<Slider>>> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
            }
        });
    }
}
