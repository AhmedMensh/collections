package com.android.collections.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class FlashSale(@SerializedName("cat_name")
                     @Expose
                     var catName: String? = null,
                     @SerializedName("branch_name")
                     @Expose
                     var branchName: String? = null,
                     @SerializedName("ID")
                     @Expose
                     var id: Int? = null,
                     @SerializedName("name")
                     @Expose
                     var name: String? = null,
                     @SerializedName("product_type")
                     @Expose
                     var productType: String? = null,
                     @SerializedName("start_date")
                     @Expose
                     var startDate: String? = null,
                     @SerializedName("end_date")
                     @Expose
                     var endDate: String? = null,
                     @SerializedName("main_img")
                     @Expose
                     var mainImg: String? = null,
                     @SerializedName("price")
                     @Expose
                     var price: Int? = null,
                     @SerializedName("price_offer")
                     @Expose
                     var priceOffer: Int? = null,
                     @SerializedName("save_price")
                     @Expose
                     var savePrice: Int? = null,
                     @SerializedName("in_favourite")
                     @Expose
                     var inFavourite: Any? = null)