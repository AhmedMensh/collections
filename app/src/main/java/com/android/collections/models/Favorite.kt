package com.android.collections.models


import com.google.gson.annotations.SerializedName

data class Favorite(
    @SerializedName("currency_name")
    var currencyName: Any? = null,
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
    @SerializedName("save_price")
    var savePrice: Int? = null
)