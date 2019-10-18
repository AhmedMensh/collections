package com.android.collections.ui.fragments.cart;

import com.android.collections.models.ApiResponse;
import com.android.collections.models.CartItem;
import com.android.collections.models.PaymentResponse;
import com.android.collections.models.TopOffer;

import java.util.List;

public interface CartViewInf {

    void displayCartItems(ApiResponse<List<CartItem>> response);

    void displayProductsLikeMe(List<TopOffer> data);

    void checkoutResponse(ApiResponse<PaymentResponse> response);
}
