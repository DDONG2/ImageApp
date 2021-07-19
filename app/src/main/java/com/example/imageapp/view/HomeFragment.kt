package com.example.imageapp.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imageapp.BaseFragment
import com.example.imageapp.R
import com.example.imageapp.databinding.FragmentHomeBinding
import com.example.imageapp.model.vo.ImageVO
import com.example.imageapp.view.adapter.ImageRecyclerAdapter
import com.example.imageapp.viewmodel.MainViewModel

class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>() {

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this).get(MainViewModel::class.java)


    override val layoutId: Int
        get() = R.layout.fragment_home

    private lateinit var mainAdapter: ImageRecyclerAdapter

    private val ItemClickListener: (ImageVO) -> Unit = {
        HomeFragmentDirections.actionHomeFragmentToDetailFragment(it).navigate()
    }


    override fun createObserveData() {

        viewModel.ImageLiveData.observe(this, Observer {

            mainAdapter.setImageList(it.documents)

            dataBinding.loadingProgressBar.visibility = View.GONE
        })

        viewModel.ErrorMessage.observe(this, {
            Log.d("Error", it)
            dataBinding.loadingProgressBar.visibility = View.GONE
        })


    }

    override fun initView() {

        mainAdapter = ImageRecyclerAdapter(ItemClickListener)
        dataBinding.recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = mainAdapter
        }

        dataBinding.loadingProgressBar.visibility = View.VISIBLE

        viewModel.getImageInfo("", 1)

    }

    override fun initArgument(bundle: Bundle) {

    }


    companion object {

        fun newInstance() = HomeFragment()

    }
}