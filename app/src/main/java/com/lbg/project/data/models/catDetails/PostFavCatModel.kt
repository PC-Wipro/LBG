package com.lbg.project.data.models.catDetails

import com.google.gson.annotations.SerializedName

data class PostFavCatModel(
    @SerializedName("image_id")
    val imageId: String,
    @SerializedName("sub_id")
    val subId: String
)