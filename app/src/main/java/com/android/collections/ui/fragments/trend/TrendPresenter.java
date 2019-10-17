package com.android.collections.ui.fragments.trend;

import android.util.Log;

import com.android.collections.helpers.PublicViewInf;
import com.android.collections.models.ApiResponse;
import com.android.collections.models.NewTrend;
import com.android.collections.models.Slider;
import com.android.collections.network.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class TrendPresenter {

    TrendViewInf viewInf;
    PublicViewInf publicViewInf;

    public TrendPresenter(TrendViewInf viewInf, PublicViewInf publicViewInf) {
        this.viewInf = viewInf;
        this.publicViewInf = publicViewInf;
    }


    public void getSliderImages(){
        Service.Fetcher.getInstance().getSliderImages("en","trends",0).enqueue(new Callback<List<Slider>>() {
            @Override
            public void onResponse(Call<List<Slider>> call, Response<List<Slider>> response) {
                try{

                    viewInf.displaySliderImages(response.body());
                }catch (Exception e){
                    Log.e(TAG, "onResponse: "+e.getLocalizedMessage() );
                }
            }

            @Override
            public void onFailure(Call<List<Slider>> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }
    public void getTrends(){

        Service.Fetcher.getInstance().getNewTrends(1,"en",0).enqueue(new Callback<ApiResponse<List<NewTrend>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<NewTrend>>> call, Response<ApiResponse<List<NewTrend>>> response) {

                if (response.body().getSuccess()){
                    viewInf.displayNewTrends(response.body().getData());
                }else {
                    publicViewInf.showMessage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<NewTrend>>> call, Throwable t) {

                Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
            }
        });
    }
}
