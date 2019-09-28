package com.android.collections.models.product_detalis


import com.google.gson.annotations.SerializedName

data class ProSizeEurope(
    @SerializedName("pro_colors")
    var proColors: ProColors? = null,
    @SerializedName("quantity")
    var quantity: Int? = null,
    @SerializedName("size_id")
    var sizeId: Int? = null,
    @SerializedName("size_name")
    var sizeName: String? = null
)