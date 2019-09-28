package com.android.collections.models.product_detalis


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("pic")
    var pic: String? = null
)