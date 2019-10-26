package com.android.dev.ahmed.collections.ui.fragments.cart;

import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.CartItem;
import com.android.dev.ahmed.collections.models.PaymentResponse;
import com.android.dev.ahmed.collections.models.TopOffer;

import java.util.List;

public interface CartViewInf {

    void displayCartItems(ApiResponse<List<CartItem>> response);

    void displayProductsLikeMe(List<TopOffer> data);

    void checkoutResponse(ApiResponse<PaymentResponse> response);
}
