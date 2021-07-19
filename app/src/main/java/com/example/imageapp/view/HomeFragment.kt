package com.example.imageapp.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.BaseFragment
import com.example.imageapp.R
import com.example.imageapp.databinding.FragmentHomeBinding
import com.example.imageapp.model.vo.ImageVO
import com.example.imageapp.view.adapter.ImageRecyclerAdapter
import com.example.imageapp.viewmodel.MainViewModel
import com.jakewharton.rxbinding4.widget.textChanges
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

const val TIME_DEBOUNCE_MILLISECONDS = 1000L

class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>() {

    private val compositeDisposable = CompositeDisposable()

    override val viewModel: MainViewModel
        get() = ViewModelProvider(this).get(MainViewModel::class.java)

    override val layoutId: Int
        get() = R.layout.fragment_home

    private lateinit var mainAdapter: ImageRecyclerAdapter

    private var SearchText: String = ""

    private val ItemClickListener: (ImageVO) -> Unit = {
        HomeFragmentDirections.actionHomeFragmentToDetailFragment(it).navigate()
    }


    override fun createObserveData() {

        viewModel.ImageLiveData.observe(this, Observer {

            if (it.size > 0) {
                mainAdapter.setImageList(it)
                dataBinding.recyclerView.visibility = View.VISIBLE
                dataBinding.emptyTextView.visibility = View.GONE
            } else {
                mainAdapter.setImageList(it)
                dataBinding.recyclerView.visibility = View.GONE
                dataBinding.emptyTextView.visibility = View.VISIBLE
            }

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
            layoutManager = GridLayoutManager(requireContext(), 3)
            adapter = mainAdapter
        }

        val subscription: Disposable =
            dataBinding.SearchEditText.textChanges()
                .debounce(TIME_DEBOUNCE_MILLISECONDS, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                    onNext = {
                        if (SearchText != it.toString() && it != "") {

                            dataBinding.loadingProgressBar.visibility = View.VISIBLE

                            SearchText = it.toString()
                            viewModel.getImageInfo(it.toString())
                        }
                    }
                )

        dataBinding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val lastVisibleItemPosition =
                    (recyclerView.layoutManager as LinearLayoutManager?)!!.findLastCompletelyVisibleItemPosition()
                val itemTotalCount = recyclerView.adapter!!.itemCount - 1
                // 스크롤이 끝에 도달했는지 확인
                if (!dataBinding.recyclerView.canScrollVertically(1) && lastVisibleItemPosition == itemTotalCount) {

                    dataBinding.loadingProgressBar.visibility = View.VISIBLE

                    viewModel.getMoreImageInfo(SearchText)
                }
            }
        })



        compositeDisposable.add(subscription)


    }

    override fun initArgument(bundle: Bundle) {

    }


    companion object {

        fun newInstance() = HomeFragment()

    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

}