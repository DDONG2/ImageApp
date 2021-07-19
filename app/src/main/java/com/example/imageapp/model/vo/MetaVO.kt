package com.example.imageapp.model.vo

import com.google.gson.annotations.SerializedName

data class MetaVO(
    @SerializedName("total_count")
    val total_count: Int,

    @SerializedName("pageableCount")
    val pageableCount: Int,

    @SerializedName("is_end")
    val is_end: Boolean
)
