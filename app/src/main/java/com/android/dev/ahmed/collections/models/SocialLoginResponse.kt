package com.android.dev.ahmed.collections.models


import com.google.gson.annotations.SerializedName

data class SocialLoginResponse(
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("fmctoken")
    var fmctoken: String? = null,
    @SerializedName("ID")
    var iD: String? = null,
    @SerializedName("img")
    var img: String? = null,
    @SerializedName("message")
    var message: String? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("status")
    var status: Boolean? = null,
    @SerializedName("user_mobile")
    var userMobile: String? = null
)