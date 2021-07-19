package com.example.imageapp.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.imageapp.BaseFragment
import com.example.imageapp.R
import com.example.imageapp.databinding.FragmentHomeBinding
import com.example.imageapp.viewmodel.MainViewModel

class DetailFragment : BaseFragment<FragmentHomeBinding, MainViewModel>() {

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this).get(MainViewModel::class.java)


    override val layoutId: Int
        get() = R.layout.fragment_image_detail


    override fun createObserveData() {

    }

    override fun initView() {


    }


    override fun initArgument(bundle: Bundle) {

    }


    companion object {

        fun newInstance() = HomeFragment()

    }
}