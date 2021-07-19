package com.example.imageapp.model.vo

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageVO(
    @SerializedName("collection")
    val collection: String = "",

    @SerializedName("thumbnail_url")
    val thumbnail_url: String = "",

    @SerializedName("image_url")
    val image_url: String = "",

    @SerializedName("width")
    val width: Int = 0,

    @SerializedName("height")
    val height: Int = 0,

    @SerializedName("display_sitename")
    val display_sitename: String = "",

    @SerializedName("doc_url")
    val doc_url: String = "",

    @SerializedName("datetime")
    val datetime: String = ""): Serializable
