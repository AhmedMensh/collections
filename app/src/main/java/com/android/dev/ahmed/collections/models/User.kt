package com.android.dev.ahmed.collections.models


import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    var email: String? = null,
    @SerializedName("img")
    var img: String? = null,
    @SerializedName("user_mobile")
    var userMobile: String? = null,
    @SerializedName("user_pass")
    var userPass: String? = null,
    @SerializedName("username")
    var username: String? = null
)