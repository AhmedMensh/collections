package com.android.collections.ui.activties.product_details;

import com.android.collections.helpers.PublicViewInf;
import com.android.collections.models.product_detalis.ProductDetails;
import com.android.collections.network.Service;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsPresenter {

    private static final String TAG = "ProductDetailsPresenter";
    private PublicViewInf publicViewInf;
    private ProductDetailsViewInf viewInf;

    public ProductDetailsPresenter(PublicViewInf publicViewInf, ProductDetailsViewInf viewInf) {
        this.publicViewInf = publicViewInf;
        this.viewInf = viewInf;
    }

    public void getProductDetails(){
        Service.Fetcher.getInstance().getProductDetails(27,1,"en").enqueue(new Callback<ProductDetails>() {
            @Override
            public void onResponse(Call<ProductDetails> call, Response<ProductDetails> response) {

                if (response.body().getStatus()){
                    viewInf.displayProductDetails(response.body());
                }else {
                    publicViewInf.showMessage("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<ProductDetails> call, Throwable t) {

            }
        });
    }
}
