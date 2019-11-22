package com.android.dev.ahmed.collections.ui.activties.register;

import android.util.Log;

import com.android.dev.ahmed.collections.CollectionApp;
import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;
import com.android.dev.ahmed.collections.models.RegisterResponse;
import com.android.dev.ahmed.collections.network.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {

    private static final String TAG = "RegisterPresenter";
    private PublicViewInf publicViewInf;
    private RegisterViewInf registerViewInf;

    public RegisterPresenter(PublicViewInf publicViewInf, RegisterViewInf registerViewInf) {
        this.publicViewInf = publicViewInf;
        this.registerViewInf = registerViewInf;
    }



    public void register(String userName,String userMobile,String userBirthday,String userPass,String userEmail,String fmcToken){

        publicViewInf.showProgressBar();
        Service.Fetcher.getInstance().register(userName ,userMobile ,userBirthday,userPass , userEmail,fmcToken, CollectionApp.getLanguage())
                .enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                        publicViewInf.hideProgressBar();
                        try {

                                publicViewInf.showMessage(response.body().getMessage());
                                if (response.body().getStatus()){

                                    registerViewInf.startHomeActivity();
                                }
                        }catch (Exception e){
                            Log.e(TAG, "onResponse: "+e.getLocalizedMessage() );
                        }

                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {

                        publicViewInf.hideProgressBar();
                        Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
                    }
                });
    }
}
