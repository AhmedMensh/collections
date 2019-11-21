package com.android.dev.ahmed.collections.ui.activties.shipping_address;

import android.util.Log;

import com.android.dev.ahmed.collections.helpers.PublicViewInf;
import com.android.dev.ahmed.collections.models.Address;
import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.network.Service;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddressPresener {
    private static final String TAG = "AddressPresener";
    private PublicViewInf publicViewInf;
    private AddressViewInf viewInf;

    public AddressPresener(PublicViewInf publicViewInf, AddressViewInf viewInf) {
        this.publicViewInf = publicViewInf;
        this.viewInf = viewInf;
    }

    public void getUserAddress(){

        Service.Fetcher.getInstance().getUserAddress("en",false,"ssssssssssssssssssss")
                .enqueue(new Callback<ApiResponse<List<Address>>>() {
                    @Override
                    public void onResponse(Call<ApiResponse<List<Address>>> call, Response<ApiResponse<List<Address>>> response) {
                        try{
                            if (response.isSuccessful()){
                                viewInf.displayAddressList(response.body().getData());
                            }else {
                                publicViewInf.showMessage(response.body().getMessage());
                            }
                        }catch (Exception e){
                            Log.e(TAG, "onResponse: "+e.getLocalizedMessage());
                        }
                    }

                    @Override
                    public void onFailure(Call<ApiResponse<List<Address>>> call, Throwable t) {
                        Log.e(TAG, "onFailure: "+t.getLocalizedMessage() );
                    }
                });
    }
}
