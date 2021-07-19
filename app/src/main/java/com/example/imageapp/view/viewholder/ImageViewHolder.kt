package com.example.imageapp.view.viewholder

import android.content.Context
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imageapp.R
import com.example.imageapp.databinding.ItemImageBinding
import com.example.imageapp.model.vo.ImageVO

class ImageViewHolder (itemView: View, private val context: Context, private val ItemClickListener: (product: ImageVO) -> Unit) : RecyclerView.ViewHolder(itemView) {

    private var binding: ItemImageBinding

    init {
        binding = DataBindingUtil.bind(itemView)!!
    }


    fun bind(item: ImageVO) {

        item.image_url.let{
            Glide.with(context)
                .load(it)
                .error(R.drawable.ic_launcher_background)
                .into(binding.imgView)
        }
        binding.fullCardView.setOnClickListener{
            item?.let { it -> ItemClickListener(it) }
        }


    }

}