package com.android.collections.ui.activties.login;

import android.util.Log;

import com.android.collections.helpers.PublicViewInf;
import com.android.collections.models.RegisterResponse;
import com.android.collections.network.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {

    private static final String TAG = "LoginPresenter";
    private PublicViewInf publicViewInf;
    private LoginViewInf loginViewInf;

    public LoginPresenter(PublicViewInf publicViewInf, LoginViewInf loginViewInf) {
        this.publicViewInf = publicViewInf;
        this.loginViewInf = loginViewInf;
    }



    public void login(String userName, String userPass, String fmcToken, String language){
        Service.Fetcher.getInstance().login(userName  ,userPass ,fmcToken,language)
                .enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                        try {

                            publicViewInf.showMessage(response.body().getMessage());
                            if (response.body().getStatus()){
                                loginViewInf.startHomeActivity();
                            }
                        }catch (Exception e){
                            Log.e(TAG, "onResponse: "+e.getLocalizedMessage() );
                        }

                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {

                        Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
                    }
                });
    }
}
