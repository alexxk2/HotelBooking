package com.example.hotelbooking.presentation.hotel.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hotelbooking.R
import com.example.hotelbooking.databinding.ImageItemBinding
import com.example.hotelbooking.domain.models.Hotel

class HotelAdapter : ListAdapter<String, HotelAdapter.HotelViewHolder>(DiffCallBack) {

    class HotelViewHolder(val binding: ImageItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HotelAdapter.HotelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ImageItemBinding.inflate(inflater, parent, false)

        return HotelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HotelAdapter.HotelViewHolder, position: Int) {

        val item = getItem(position)
        Glide.with(holder.binding.root)
            .load(item)
            .centerCrop()
            .placeholder(R.drawable.ic_image_placeholder2)
            .into(holder.binding.imageView)

    }

    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<String>() {

            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }
}