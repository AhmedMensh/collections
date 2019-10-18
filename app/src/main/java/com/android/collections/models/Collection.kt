package com.android.collections.models


import com.google.gson.annotations.SerializedName

data class Collection(
    @SerializedName("branch_name")
    var branchName: String? = null,
    @SerializedName("cat_name")
    var catName: String? = null,
    @SerializedName("item_found")
    var itemFound: Int? = null,
    @SerializedName("products")
    var products: List<Product?>? = null,
    @SerializedName("status")
    var status: Boolean? = null
) {
    data class Product(
        @SerializedName("currency_name")
        var currencyName: String? = null,
        @SerializedName("ID")
        var iD: Int? = null,
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
        @SerializedName("product_type")
        var productType: Any? = null,
        @SerializedName("save_price")
        var savePrice: Int? = null
    )
}