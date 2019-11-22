package com.android.dev.ahmed.collections.models


import com.google.gson.annotations.SerializedName

data class AddAddressResponse(
    @SerializedName("address_name")
    val addressName: String? = null,
    @SerializedName("Building_no")
    val buildingNo: String? = null,
    @SerializedName("city")
    val city: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("default_address")
    val defaultAddress: String? = null,
    @SerializedName("floor_no")
    val floorNo: String? = null,
    @SerializedName("full_name")
    val fullName: String? = null,
    @SerializedName("ID")
    val iD: String? = null,
    @SerializedName("latitude")
    val latitude: String? = null,
    @SerializedName("longitude")
    val longitude: String? = null,
    @SerializedName("mobile")
    val mobile: String? = null,
    @SerializedName("user_id")
    val userId: Int? = null
)