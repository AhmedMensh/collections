package com.android.collections.models


import com.google.gson.annotations.SerializedName

data class Order(
    @SerializedName("date_payment")
    var datePayment: String? = null,
    @SerializedName("ID")
    var iD: Int? = null,
    @SerializedName("img")
    var img: String? = null,
    @SerializedName("price")
    var price: Int? = null,
    @SerializedName("pro_details")
    var proDetails: String? = null,
    @SerializedName("product_name")
    var productName: String? = null,
    @SerializedName("quantity")
    var quantity: Int? = null,
    @SerializedName("size")
    var size: String? = null
)