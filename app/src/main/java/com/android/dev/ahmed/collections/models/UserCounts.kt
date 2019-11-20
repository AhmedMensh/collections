package com.android.dev.ahmed.collections.models


import com.google.gson.annotations.SerializedName

data class UserCounts(
        @SerializedName("cart")
        val cart: Cart? = null,
        @SerializedName("orders")
        val orders: Orders? = null,
        @SerializedName("products")
        val products: Products? = null,
        @SerializedName("wishList")
        val wishList: WishList? = null
) {
    data class Cart(
            @SerializedName("cart_count")
            val cartCount: Int? = null
    )

    data class Orders(
            @SerializedName("orders_count")
            val ordersCount: Int? = null
    )

    data class Products(
            @SerializedName("products_count")
            val productsCount: Int? = null
    )

    data class WishList(
            @SerializedName("wishList_count")
            val wishListCount: Int? = null
    )
}