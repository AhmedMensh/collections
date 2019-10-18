package com.android.collections.models


import com.google.gson.annotations.SerializedName

data class PaymentResponse(
    @SerializedName("address_name")
    var addressName: String? = null,
    @SerializedName("all_price_after_promo")
    var allPriceAfterPromo: String? = null,
    @SerializedName("city")
    var city: String? = null,
    @SerializedName("country")
    var country: String? = null,
    @SerializedName("default_address")
    var defaultAddress: String? = null,
    @SerializedName("delivery")
    var delivery: String? = null,
    @SerializedName("full_name")
    var fullName: String? = null,
    @SerializedName("mobile")
    var mobile: String? = null,
    @SerializedName("order_id")
    var orderId: Int? = null,
    @SerializedName("paytype")
    var paytype: String? = null,
    @SerializedName("price_delivery")
    var priceDelivery: String? = null,
    @SerializedName("price_pro")
    var pricePro: String? = null,
    @SerializedName("promo_price")
    var promoPrice: String? = null,
    @SerializedName("quantity")
    var quantity: Int? = null,
    @SerializedName("status")
    var status: Boolean? = null,
    @SerializedName("total_price")
    var totalPrice: Int? = null
)