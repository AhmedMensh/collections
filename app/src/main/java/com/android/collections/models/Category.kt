package com.android.collections.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class Category(
        @SerializedName("id")
        @Expose
        var id : Int ? =null,
        @SerializedName("name")
        @Expose
        var name : String ? = null,
        @SerializedName("img")
        @Expose
        var img : String ? = null,
        @SerializedName("sub_cat")
        @Expose
        var subCategories : List<SubCat> ? = null
)