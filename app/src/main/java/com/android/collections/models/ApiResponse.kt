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
        val success: Boolean? = null
)