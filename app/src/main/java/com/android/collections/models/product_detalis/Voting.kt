package com.android.collections.models.product_detalis

import com.google.gson.annotations.SerializedName

data class Voting(@SerializedName("voting_count")
                  var votingCount: Int? = null,
                  @SerializedName("all_voting")
                  var allVoting: Int? = null)