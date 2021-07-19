package com.example.imageapp.model.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private val client by lazy {
        OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }.build()
    }

    private const val BASE_URL = "https://dapi.kakao.com"

    const val KAKAO_API_KEY = "80a32a0c52f4bc98760bc4e24365e2af"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    val service: RetrofitService = retrofit.create(RetrofitService::class.java)


    fun get(): RetrofitService {
        return service;
    }
}