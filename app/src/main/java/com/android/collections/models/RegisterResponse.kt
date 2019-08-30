package com.android.collections.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RegisterResponse {

    @SerializedName("status")
    @Expose
    var status: Boolean? = null
    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("ID")
    @Expose
    var id: Int? = null
    @SerializedName("fmctoken")
    @Expose
    var fmctoken: String? = null
    @SerializedName("username")
    @Expose
    var username: String? = null
    @SerializedName("user_mobile")
    @Expose
    var userMobile: String? = null
    @SerializedName("email")
    @Expose
    var email: String? = null


}
