package com.example.imageapp.model.repository

import com.example.imageapp.model.network.RetrofitClient
import com.example.imageapp.model.vo.ImageVO
import com.example.imageapp.model.vo.KakaoImageResponse
import retrofit2.Response

class ImageRepositoryImpl :ImageRepository{

    override suspend fun requestImageApi(title: String, page: Int): Response<KakaoImageResponse>
    = RetrofitClient.get().searchImage(title = title, page = page)

}