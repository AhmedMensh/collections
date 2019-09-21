package com.android.collections.ui.fragments.trend;

import com.android.collections.helpers.PublicViewInf;
import com.android.collections.models.ApiResponse;
import com.android.collections.models.NewTrend;
import com.android.collections.network.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrendPresenter {

    TrendViewInf viewInf;
    PublicViewInf publicViewInf;

    public TrendPresenter(TrendViewInf viewInf, PublicViewInf publicViewInf) {
        this.viewInf = viewInf;
        this.publicViewInf = publicViewInf;
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

            }
        });
    }
}
