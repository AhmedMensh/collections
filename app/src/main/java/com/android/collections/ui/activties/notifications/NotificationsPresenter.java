package com.android.collections.ui.activties.notifications;

import com.android.collections.helpers.PublicViewInf;
import com.android.collections.models.ApiResponse;
import com.android.collections.models.NewArrival;
import com.android.collections.models.Notification;
import com.android.collections.network.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationsPresenter {

    private static final String TAG = "NotificationsPresenter";
    private PublicViewInf publicViewInf;
    private NotificationViewInf notificationViewInf;

    public NotificationsPresenter(PublicViewInf publicViewInf, NotificationViewInf notificationViewInf) {
        this.publicViewInf = publicViewInf;
        this.notificationViewInf = notificationViewInf;
    }


    public void getNotifications(){

        publicViewInf.showProgressBar();

        Service.Fetcher.getInstance().getNotifications(0,"ar").enqueue(new Callback<List<Notification>>() {
            @Override
            public void onResponse(Call<List<Notification>> call, Response<List<Notification>> response) {

                publicViewInf.hideProgressBar();
                if (response.isSuccessful()){
                    try {
                        notificationViewInf.displayNotifications(response.body());
                    }catch (Exception e){

                    }
                }
            }

            @Override
            public void onFailure(Call<List<Notification>> call, Throwable t) {

            }
        });
    }
}
