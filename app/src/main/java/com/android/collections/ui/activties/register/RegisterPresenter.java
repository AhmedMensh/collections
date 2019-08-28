package com.android.collections.ui.activties.register;

import android.content.Context;
import android.util.Log;

import com.android.collections.helpers.PublicViewInf;
import com.android.collections.models.RegisterResponse;
import com.android.collections.network.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterPresenter {

    private static final String TAG = "RegisterPresenter";
    private PublicViewInf publicViewInf;

    public RegisterPresenter(PublicViewInf publicViewInf) {
        this.publicViewInf = publicViewInf;
    }

//    @Query("username") String userName,
//    @Query("user_mobile") String userMobile,
//    @Query("user_pass") String userPass,
//    @Query("email") String userEmail,
//    @Query("fmctoken") String fmcToken,
//    @Query("lang") String language);

    public void register(String userName,String userMobile,String userPass,String userEmail,String fmcToken,String language){
        Service.Fetcher.getInstance().register(userName ,userMobile ,userPass , userEmail,fmcToken,language)
                .enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {

                        try {

                                publicViewInf.showMessage(response.body().getMessage());
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
