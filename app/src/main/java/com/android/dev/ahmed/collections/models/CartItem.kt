package com.android.dev.ahmed.collections.models


import com.google.gson.annotations.SerializedName

data class CartItem(
        @SerializedName("cart_id")
        var cartId: Int? = null,
        @SerializedName("color_code")
        var colorCode: String? = null,
        @SerializedName("color_id")
        var colorId: Int? = null,
        @SerializedName("color_name")
        var colorName: String? = null,
        @SerializedName("currency_name")
        var currencyName: String? = null,
        @SerializedName("ID")
        var iD: Int? = null,
        @SerializedName("main_img")
        var mainImg: String? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("price")
        var price: Int? = null,
        @SerializedName("price_offer")
        var priceOffer: Int? = null,
        @SerializedName("products_price")
        var productsPrice: Int? = null,
        @SerializedName("quantity")
        var quantity: Int? = null,
        @SerializedName("seller_id")
        var sellerId: Int? = null,
        @SerializedName("seller_name")
        var sellerName: String? = null,
        @SerializedName("size_id")
        var sizeId: Int? = null,
        @SerializedName("size_name")
        var sizeName: String? = null
)