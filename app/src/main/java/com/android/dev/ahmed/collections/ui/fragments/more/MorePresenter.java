package com.android.dev.ahmed.collections.ui.fragments.more;

import android.content.Context;
import android.util.Log;

import com.android.dev.ahmed.collections.helpers.Constants;
import com.android.dev.ahmed.collections.helpers.SharedPreferencesManager;
import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.UserCounts;
import com.android.dev.ahmed.collections.network.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MorePresenter {

    private static final String TAG = "MorePresenter";
    private MoreViewInf viewInf;
    private Context context;

    public MorePresenter(MoreViewInf viewInf, Context context) {
        this.viewInf = viewInf;
        this.context = context;
    }

    public void getUserCounts(){
        Service.Fetcher.getInstance().getUserCounts(SharedPreferencesManager.getIntValue(context, Constants.USER_ID))
                .enqueue(new Callback<ApiResponse<UserCounts>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<UserCounts>> call, Response<ApiResponse<UserCounts>> response) {
                        try {

                            viewInf.displayUserCounts(response.body().getData());
                        }catch (Exception e){

                            Log.e(TAG, "onResponse: "+e.getLocalizedMessage() );
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<UserCounts>> call, Throwable t) {

                        Log.e(TAG, "onFailure: "+t.getLocalizedMessage());
                    }
                });
    }
}
