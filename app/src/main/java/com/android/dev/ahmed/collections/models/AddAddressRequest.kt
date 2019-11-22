package com.android.dev.ahmed.collections.models

data class AddAddressRequest(
        var addressName: String? = null,
        var country: String? = null,
        var city: String? = null,
        var buildingNumber: String? = null,
        var floorNumber: String? = null,
        var mobile: String? = null,
        var fullName: String? = null,

        var addressId: Int? = null,
        var type: String? = null,
        var defaultAddress: String? = null,
        var language: String? = null,
        var isRegistered: Boolean,
        var macAddress: String? = null,
        var lat: String? = null,
        var log: String? = null
)