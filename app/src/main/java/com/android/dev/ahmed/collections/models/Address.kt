package com.android.dev.ahmed.collections.models


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("address")
    val address: String? = null,
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
    val userId: String? = null
) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(address)
        parcel.writeString(buildingNo)
        parcel.writeString(city)
        parcel.writeString(country)
        parcel.writeString(defaultAddress)
        parcel.writeString(floorNo)
        parcel.writeString(fullName)
        parcel.writeString(iD)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
        parcel.writeString(mobile)
        parcel.writeString(userId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Address> {
        override fun createFromParcel(parcel: Parcel): Address {
            return Address(parcel)
        }

        override fun newArray(size: Int): Array<Address?> {
            return arrayOfNulls(size)
        }
    }
}