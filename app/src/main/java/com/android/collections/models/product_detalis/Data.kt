package com.android.collections.models.product_detalis


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("details")
    var details: String? = null,
    @SerializedName("ID")
    var iD: Int? = null,
    @SerializedName("images")
    var images: List<Image?>? = null,
    @SerializedName("in_favourite")
    var inFavourite: Boolean? = null,
    @SerializedName("main_img")
    var mainImg: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("price")
    var price: Int? = null,
    @SerializedName("price_offer")
    var priceOffer: Int? = null,
    @SerializedName("pro_size_arabic")
    var proSizeArabic: List<ProSizeArabic?>? = null,
    @SerializedName("pro_size_europe")
    var proSizeEurope: List<ProSizeEurope?>? = null,
    @SerializedName("save_price")
    var savePrice: Int? = null,
    @SerializedName("seller_id")
    var sellerId: Int? = null,
    @SerializedName("seller_name")
    var sellerName: String? = null,
    @SerializedName("voting")
    var voting: Voting? = null
)