package com.android.dev.ahmed.collections.ui.activties.login;

import android.content.Context;
import android.util.Log;

import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;
import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.FacebookLoginResponse;
import com.android.dev.ahmed.collections.models.RegisterResponse;
import com.android.dev.ahmed.collections.network.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    private static final String TAG = "LoginPresenter";
    private PublicViewInf publicViewInf;
    private LoginViewInf loginViewInf;
    private Context context;

    public LoginPresenter(PublicViewInf publicViewInf, LoginViewInf loginViewInf,Context context) {
        this.publicViewInf = publicViewInf;
        this.loginViewInf = loginViewInf;
        this.context = context;
    }



    public void login(String userName, String userPass, String fmcToken, String language){

        publicViewInf.showProgressBar();
        Service.Fetcher.getInstance().login(userName  ,userPass ,fmcToken,language)
                .enqueue(new Callback<ApiResponse<RegisterResponse>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<RegisterResponse>> call, Response<ApiResponse<RegisterResponse>> response) {

                        publicViewInf.hideProgressBar();
                        try {


                            Log.e(TAG, "onResponse: "+response.body().getMessage());
                            if (response.body().getSuccess()){
                                SharedPreferencesManager.setIntValue(context, Constants.USER_ID,response.body().getData().getId());
                                loginViewInf.startHomeActivity();
                            }else   publicViewInf.showMessage(response.body().getMessage());
                        }catch (Exception e){
                            Log.e(TAG, "onResponse: "+e.getLocalizedMessage() );
                        }

                    }

                    @Override
                    public void onFailure(Call<ApiResponse<RegisterResponse>> call, Throwable t) {

                        publicViewInf.hideProgressBar();
                        Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
                    }
                });
    }


    public void loginWithFacebook(String name , String email ,String fcmToken ,String picture){

        Service.Fetcher.getInstance().loginWithFacebook(name, email, fcmToken, picture).enqueue(new Callback<FacebookLoginResponse>() {
            @Override
            public void onResponse(Call<FacebookLoginResponse> call, Response<FacebookLoginResponse> response) {

                try{

                    if (response.body().getStatus()){
                        Log.e(TAG, "onResponse: "+response.body().getID());
                        SharedPreferencesManager.setIntValue(context, Constants.USER_ID,Integer.parseInt(response.body().getID()));
                        loginViewInf.startHomeActivity();
                    }else {
                        publicViewInf.showMessage(response.body().getMessage());
                    }
                }catch (Exception e){

                    Log.e(TAG, "onResponse: "+e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(Call<FacebookLoginResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
            }
        });
    }
}
