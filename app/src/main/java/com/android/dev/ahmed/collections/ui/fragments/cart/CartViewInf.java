package com.android.dev.ahmed.collections.ui.fragments.cart;

import com.android.dev.ahmed.collections.models.ApiResponse;
import com.android.dev.ahmed.collections.models.CartItems;
import com.android.dev.ahmed.collections.models.PaymentResponse;
import com.android.dev.ahmed.collections.models.TopOffer;

import java.util.List;

public interface CartViewInf {

    void displayCartItems(CartItems cartItems);

    void checkoutResponse(ApiResponse<PaymentResponse> response);

    void getDefaultAddress(String address);
}
