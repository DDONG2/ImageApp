package com.example.imageapp.model.repository

import com.example.imageapp.model.vo.ImageVO
import com.example.imageapp.model.vo.KakaoImageResponse
import retrofit2.Response

interface ImageRepository {

    suspend fun requestImageApi(title: String, page: Int) : Response<KakaoImageResponse>

}