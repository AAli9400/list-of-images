package com.example.listofimages.ui.home_fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listofimages.data.model.ImageWithTitle
import com.example.listofimages.databinding.ImagesGridItemBinding
import javax.inject.Inject

class ImagesListAdapter @Inject constructor() :
    PagingDataAdapter<ImageWithTitle, ImagesListAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ImagesGridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ImagesGridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageWithTitle: ImageWithTitle?) {
            imageWithTitle?.let {
                binding.imageWithTitle = it
            }
        }
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ImageWithTitle>() {
            override fun areItemsTheSame(
                oldItem: ImageWithTitle,
                newItem: ImageWithTitle
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: ImageWithTitle,
                newItem: ImageWithTitle
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}