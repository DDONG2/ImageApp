package com.example.imageapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<LAYOUT: ViewDataBinding, VM: BaseViewModel> : AppCompatActivity() {
    lateinit var dataBinding: LAYOUT

    abstract val viewModel: VM

    abstract val layoutId: Int

    abstract fun createObserveData()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, layoutId)

        createObserveData()

    }
}