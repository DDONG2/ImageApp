package com.example.imageapp.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.imageapp.BaseFragment
import com.example.imageapp.R
import com.example.imageapp.databinding.FragmentImageDetailBinding
import com.example.imageapp.viewmodel.MainViewModel

class DetailFragment : BaseFragment<FragmentImageDetailBinding, MainViewModel>() {

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this).get(MainViewModel::class.java)


    override val layoutId: Int
        get() = R.layout.fragment_image_detail

    val args: DetailFragmentArgs by navArgs()

    override fun createObserveData() {

    }

    override fun initView() {
        dataBinding.imgBack.setOnClickListener {
            findNavController().popBackStack()
        }

        Glide.with(requireActivity())
            .load(args.imageVO?.image_url)
            .error(R.drawable.ic_launcher_background)
            .into(dataBinding.imgView)

        args.imageVO?.display_sitename?.let {
            dataBinding.nameTextView.text = it
        }

        args.imageVO?.datetime?.let {
            dataBinding.timeTextView.text = it
        }

    }


    override fun initArgument(bundle: Bundle) {

    }

    companion object {

        fun newInstance() = HomeFragment()

    }
}