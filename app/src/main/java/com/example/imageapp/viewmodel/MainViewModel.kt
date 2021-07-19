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

    private val _ImageLiveData = MutableLiveData<KakaoImageResponse>()
    val ImageLiveData: LiveData<KakaoImageResponse>
        get() = _ImageLiveData


    private val _ErrorMessage = MutableLiveData<String>()
    val ErrorMessage: LiveData<String>
        get() = _ErrorMessage



    fun getImageInfo(title: String, page: Int) {
        viewModelScope.launch {
            val response = imageRepository.requestImageApi(title, page)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {

                    _ImageLiveData.value = response.body()

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


