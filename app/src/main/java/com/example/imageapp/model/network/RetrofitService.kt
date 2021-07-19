package com.example.imageapp.model.network

import com.example.imageapp.model.network.RetrofitClient.KAKAO_API_KEY
import com.example.imageapp.model.vo.ImageVO
import com.example.imageapp.model.vo.KakaoImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

const val REQUEST_IMAGE_LIST_SIZE_DEFAULT: Int = 30
const val REQUEST_IMAGE_LIST_TYPE_DEFAULT: String = "accuracy"

interface RetrofitService {

    @GET("/v2/search/image")
    @Headers("Authorization: KakaoAK $KAKAO_API_KEY")
    suspend fun searchImage(@Query("query") title: String,
                              @Query("sort") sort: String = REQUEST_IMAGE_LIST_TYPE_DEFAULT,
                              @Query("page") page: Int,
                              @Query("size") size: Int = REQUEST_IMAGE_LIST_SIZE_DEFAULT): Response<KakaoImageResponse<ImageVO>>

}