package com.android.collections.ui.activties.orders;

import android.content.Context;

import com.android.collections.helpers.Constants;
import com.android.collections.helpers.PublicViewInf;
import com.android.collections.helpers.SharedPreferencesManager;
import com.android.collections.models.ApiResponse;
import com.android.collections.models.Order;
import com.android.collections.network.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersPresenter {

    private static final String TAG = "OrdersPresenter";
    private PublicViewInf publicViewInf;
    private OrdersViewInf viewInf;
    private Context context;

    public OrdersPresenter(PublicViewInf publicViewInf, OrdersViewInf viewInf) {
        this.publicViewInf = publicViewInf;
        this.viewInf = viewInf;

    }

    public void getMyOrders(int userId){

        Service.Fetcher.getInstance().getMyOrders(userId,"en").enqueue(new Callback<ApiResponse<List<Order>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Order>>> call, Response<ApiResponse<List<Order>>> response) {

                if (response.body().getSuccess()){
                    viewInf.displayOrders(response.body().getData());
                }else {
                    publicViewInf.showMessage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Order>>> call, Throwable t) {

            }
        });
    }
}
