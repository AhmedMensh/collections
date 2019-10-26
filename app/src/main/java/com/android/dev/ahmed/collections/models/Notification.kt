package com.android.dev.ahmed.collections.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Notification(
        @SerializedName("status")
        @Expose
        var status: Boolean? = null,
        @SerializedName("title")
        @Expose
        var title: String? = null,
        @SerializedName("message")
        @Expose
        var message: String? = null,
        @SerializedName("user_id")
        @Expose
        var userId: Int? = null, @SerializedName("order_id")
        @Expose
        var orderId: Int? = null,
        @SerializedName("notifcation_type")
        @Expose
        var notifcationType: String? = null,
        @SerializedName("notifcations_date")
        @Expose
        var notifcationsDate: String? = null

)