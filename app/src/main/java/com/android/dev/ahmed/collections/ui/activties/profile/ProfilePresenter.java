package com.android.dev.ahmed.collections.ui.activties.profile;

import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.User;
import com.android.dev.ahmed.collections.network.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilePresenter {

    private static final String TAG = "ProfilePresenter";
    private PublicViewInf publicViewInf;
    private ProfileViewInf viewInf;


    public ProfilePresenter(PublicViewInf publicViewInf, ProfileViewInf viewInf) {
        this.publicViewInf = publicViewInf;
        this.viewInf = viewInf;
    }

    public void getUserProfile(){
        Service.Fetcher.getInstance().getUserProfile("en").enqueue(new Callback<ApiResponse<User>>() {
            @Override
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {

                if (response.body().getSuccess()){
                    viewInf.displayProfileDetails(response.body().getData());
                }else {
                    publicViewInf.showMessage(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<User>> call, Throwable t) {

            }
        });
    }
}
