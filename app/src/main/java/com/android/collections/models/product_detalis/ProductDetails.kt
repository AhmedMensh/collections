package com.android.collections.models.product_detalis


import com.google.gson.annotations.SerializedName

data class ProductDetails(
    @SerializedName("branch_name")
    var branchName: String? = null,
    @SerializedName("cat_name")
    var catName: String? = null,
    @SerializedName("data")
    var `data`: Data? = null,
    @SerializedName("status")
    var status: Boolean? = null
)