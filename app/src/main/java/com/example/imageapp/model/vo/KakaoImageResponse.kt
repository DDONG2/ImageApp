package com.example.imageapp.model.vo

import com.google.gson.annotations.SerializedName

data class KakaoImageResponse<T>(
    @SerializedName("meta")
    val meta: MetaVO,

    @SerializedName("documents")
    val documents: List<T>
)
