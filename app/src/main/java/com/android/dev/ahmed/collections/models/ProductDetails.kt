package com.android.dev.ahmed.collections.models


import com.google.gson.annotations.SerializedName

data class ProductDetails(
    @SerializedName("branch_name")
    var branchName: String? = null,
    @SerializedName("cat_name")
    var catName: String? = null,
    @SerializedName("data")
    var `data`: Data? = null
) {
    data class Data(
        @SerializedName("branch_id")
        var branchId: Int? = null,
        @SerializedName("details")
        var details: String? = null,
        @SerializedName("ID")
        var iD: Int? = null,
        @SerializedName("images")
        var images: List<Image?>? = null,
        @SerializedName("in_favourite")
        var inFavourite: Boolean? = null,
        @SerializedName("main_img")
        var mainImg: String? = null,
        @SerializedName("name")
        var name: String? = null,
        @SerializedName("price")
        var price: Int? = null,
        @SerializedName("price_offer")
        var priceOffer: Int? = null,
        @SerializedName("product_type")
        var productType: String? = null,
        @SerializedName("save_price")
        var savePrice: Int? = null,
        @SerializedName("seller_id")
        var sellerId: Int? = null,
        @SerializedName("seller_name")
        var sellerName: String? = null,
        @SerializedName("size")
        var size: List<Size?>? = null,
        @SerializedName("voting")
        var voting: Voting? = null
    ) {
        data class Image(
            @SerializedName("pic")
            var pic: String? = null
        )

        data class Size(
            @SerializedName("color")
            var color: List<Color?>? = null,
            @SerializedName("quantity")
            var quantity: Int? = null,
            @SerializedName("size_id")
            var sizeId: Int? = null,
            @SerializedName("size_name")
            var sizeName: String? = null
        ) {
            data class Color(
                @SerializedName("color_code")
                var colorCode: String? = null,
                @SerializedName("color_id")
                var colorId: Int? = null,
                @SerializedName("color_name")
                var colorName: String? = null
            )
        }

        data class Voting(
            @SerializedName("all_voting")
            var allVoting: Int? = null,
            @SerializedName("voting_count")
            var votingCount: Double? = null
        )
    }
}