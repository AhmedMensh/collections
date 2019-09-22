package com.android.collections.ui.fragments.cart;

import com.android.collections.models.ApiResponse;
import com.android.collections.models.CartItem;

import java.util.List;

public interface CartViewInf {

    void displayCartItems(ApiResponse<List<CartItem>> response);
}
