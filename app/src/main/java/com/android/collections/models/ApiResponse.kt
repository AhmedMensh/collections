package com.android.collections.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiResponse <T>(
        @SerializedName("message")
        @Expose
        val message: String? = null,
        @SerializedName("data")
        @Expose
        val data: T? = null,
        @SerializedName("status")
        @Expose
        val success: Boolean? = null,

        //case of cart items only
        @SerializedName("sub_total")
        @Expose
        var subTotal : Int ? = null,
        @SerializedName("shipping")
        @Expose
        var shipping : Int ? = null,
        @SerializedName("total")
        @Expose
        var total : Int ? = null

)