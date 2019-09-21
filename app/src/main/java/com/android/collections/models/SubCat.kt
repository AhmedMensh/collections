package com.android.collections.models


import com.google.gson.annotations.SerializedName

data class SubCat(
    @SerializedName("ID_sub")
    var iDSub: Int? = null,
    @SerializedName("img_sub")
    var imgSub: String? = null,
    @SerializedName("name_sub")
    var nameSub: String? = null
)