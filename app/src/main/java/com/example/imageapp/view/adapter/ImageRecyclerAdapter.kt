package com.example.imageapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imageapp.databinding.ItemImageBinding
import com.example.imageapp.model.vo.ImageVO
import com.example.imageapp.view.viewholder.ImageViewHolder

class ImageRecyclerAdapter(private val ItemClickListener: (ImageVo: ImageVO) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var ImageList: List<ImageVO> = listOf<ImageVO>()


    fun setImageList(productList: List<ImageVO>) {
        this.ImageList = productList
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {


        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding.root, parent.context, ItemClickListener)


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (holder is ImageViewHolder)
            holder.bind(ImageList.get(position))
    }

    override fun getItemCount(): Int {
        return ImageList.size
    }


}