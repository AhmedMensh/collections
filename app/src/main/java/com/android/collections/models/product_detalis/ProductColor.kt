package com.android.collections.models.product_detalis


import com.google.gson.annotations.SerializedName

data class ProductColor(
    @SerializedName("color_code")
    var colorCode: String? = null,
    @SerializedName("color_id")
    var colorId: Int? = null,
    @SerializedName("color_name")
    var colorName: String? = null
)