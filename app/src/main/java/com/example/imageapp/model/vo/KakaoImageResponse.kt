package com.example.imageapp.model.vo

import com.google.gson.annotations.SerializedName

data class KakaoImageResponse(
    @SerializedName("meta")
    var meta: MetaVO,

    @SerializedName("documents")
    var documents: List<ImageVO>
)
