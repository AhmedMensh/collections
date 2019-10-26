package com.android.dev.ahmed.collections.models


import com.google.gson.annotations.SerializedName

data class UserCounts(
    @SerializedName("cart")
    var cart: Cart? = null,
    @SerializedName("orders")
    var orders: Orders? = null,
    @SerializedName("products")
    var products: Products? = null,
    @SerializedName("wishList")
    var wishList: WishList? = null
) {
    data class Cart(
        @SerializedName("cart_count")
        var cartCount: Int? = null
    )

    data class Orders(
        @SerializedName("orders_count")
        var ordersCount: Int? = null
    )

    data class Products(
        @SerializedName("products_count")
        var productsCount: Int? = null
    )

    data class WishList(
        @SerializedName("wishList_count")
        var wishListCount: Int? = null
    )
}