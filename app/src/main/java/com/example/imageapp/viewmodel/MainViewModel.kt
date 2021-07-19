package com.example.imageapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.imageapp.BaseViewModel
import com.example.imageapp.model.repository.ImageRepository
import com.example.imageapp.model.repository.ImageRepositoryImpl
import com.example.imageapp.model.vo.ImageVO
import com.example.imageapp.model.vo.KakaoImageResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel : BaseViewModel() {

    private val imageRepository: ImageRepository

    init {
        imageRepository = ImageRepositoryImpl()
    }

    private val _ImageLiveData = MutableLiveData<List<ImageVO>>()
    val ImageLiveData: LiveData<List<ImageVO>>
        get() = _ImageLiveData


    private val _ErrorMessage = MutableLiveData<String>()
    val ErrorMessage: LiveData<String>
        get() = _ErrorMessage

    private val ImageAllList = mutableListOf<ImageVO>()

    private var pageNumber: Int = 1

    fun getImageInfo(title: String) {

        pageNumber = 1
        ImageAllList.clear()

        viewModelScope.launch {
            val response = imageRepository.requestImageApi(title, pageNumber)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    response.body()?.documents?.forEach() {
                        ImageAllList.add(it)
                    }

                    _ImageLiveData.value = ImageAllList

                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }

    }

    fun getMoreImageInfo(title: String) {

        pageNumber++

        viewModelScope.launch {
            val response = imageRepository.requestImageApi(title, pageNumber)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    response.body()?.documents?.forEach {
                        ImageAllList.add(it)
                    }

                    _ImageLiveData.value = ImageAllList

                } else {
                    onError("Error : ${response.message()} ")
                }
            }
        }
    }


        private fun onError(message: String) {
        _ErrorMessage.value = message
    }
}


