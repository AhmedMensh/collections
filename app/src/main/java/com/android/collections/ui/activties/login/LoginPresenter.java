package com.android.collections.ui.activties.login;

import android.content.Context;
import android.util.Log;

import com.android.collections.helpers.Constants;
import com.android.collections.helpers.PublicViewInf;
import com.android.collections.helpers.SharedPreferencesManager;
import com.android.collections.models.ApiResponse;
import com.android.collections.models.RegisterResponse;
import com.android.collections.network.Service;

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
}
