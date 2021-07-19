package com.example.imageapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imageapp.BaseViewModel
import com.example.imageapp.model.repository.ImageRepository
import com.example.imageapp.model.repository.ImageRepositoryImpl

class MainViewModel : BaseViewModel() {

    private val productRepository: ImageRepository

    init {
        productRepository = ImageRepositoryImpl()
    }

    private val _ErrorMessage = MutableLiveData<String>()
    val ErrorMessage: LiveData<String>
        get() = _ErrorMessage



    fun getMoreProductInfo() {


    }

    private fun onError(message: String) {
        _ErrorMessage.value = message
    }
}


