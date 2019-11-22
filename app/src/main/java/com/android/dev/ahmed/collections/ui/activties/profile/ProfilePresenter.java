package com.android.dev.ahmed.collections.ui.activties.profile;

import android.util.Log;

import com.android.dev.ahmed.collections.CollectionApp;
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

    public void updateProfile(String userName,String mobile ,String birthday,String pass,String email){
        Service.Fetcher.getInstance().updateProfile(CollectionApp.getUserId(),userName,mobile,birthday,pass,email)
                .enqueue(new Callback<ApiResponse<User>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                        try{
                            publicViewInf.showMessage(response.body().getMessage());
                        }catch (Exception e){
                            Log.e(TAG, "onResponse: "+e.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<User>> call, Throwable t) {
                        Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
                    }
                });
    }
    public void getUserProfile(){

        Service.Fetcher.getInstance().getUserProfile(CollectionApp.getUserId(),CollectionApp.getLanguage()).enqueue(new Callback<ApiResponse<User>>() {
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
