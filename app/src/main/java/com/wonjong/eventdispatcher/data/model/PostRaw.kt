package com.wonjong.eventdispatcher.data.model

import com.google.gson.annotations.SerializedName

data class PostRaw(
    @SerializedName("id")
    val id: Int,

    @SerializedName("body")
    val body: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("userId")
    val userId: Int
)