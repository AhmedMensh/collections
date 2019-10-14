package com.android.collections.models

import com.google.gson.annotations.SerializedName

data class Slider(
        @SerializedName("ID")
        var id: Int? = null,
        @SerializedName("img")
        var img: String? = null,
        @SerializedName("title")
        var title: String? = null
)