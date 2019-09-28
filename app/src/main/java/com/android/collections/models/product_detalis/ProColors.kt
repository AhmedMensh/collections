package com.android.collections.models.product_detalis


import com.google.gson.annotations.SerializedName

data class ProColors(
    @SerializedName("product_colors")
    var productColors: List<ProductColor?>? = null
)