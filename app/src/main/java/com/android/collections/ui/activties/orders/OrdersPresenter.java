package com.android.collections.ui.activties.orders;

import com.android.collections.helpers.PublicViewInf;
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

    public OrdersPresenter(PublicViewInf publicViewInf, OrdersViewInf viewInf) {
        this.publicViewInf = publicViewInf;
        this.viewInf = viewInf;
    }

    public void getMyOrders(){

        Service.Fetcher.getInstance().getMyOrders(1,"en").enqueue(new Callback<ApiResponse<List<Order>>>() {
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
