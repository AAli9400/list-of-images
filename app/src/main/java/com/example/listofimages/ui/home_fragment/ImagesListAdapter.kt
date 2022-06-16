package com.example.listofimages.ui.home_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.listofimages.data.model.ImageWithTitle
import com.example.listofimages.databinding.ImagesGridItemBinding

class ImagesListAdapter :
    ListAdapter<ImageWithTitle, ImagesListAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ImagesGridItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class ViewHolder(private val binding: ImagesGridItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(imageWithTitle: ImageWithTitle) {
            binding.imageWithTitle = imageWithTitle
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ImageWithTitle>() {
        override fun areItemsTheSame(oldItem: ImageWithTitle, newItem: ImageWithTitle): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageWithTitle, newItem: ImageWithTitle): Boolean {
            return oldItem.url == newItem.url && oldItem.title == newItem.title
        }
    }
}