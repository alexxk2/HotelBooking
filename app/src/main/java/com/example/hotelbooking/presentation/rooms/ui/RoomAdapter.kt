package com.example.hotelbooking.presentation.rooms.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbooking.R
import com.example.hotelbooking.databinding.RoomItemBinding
import com.example.hotelbooking.domain.models.Room
import com.example.hotelbooking.presentation.hotel.ui.HotelAdapter
import com.google.android.material.carousel.CarouselLayoutManager


class RoomAdapter(
    private val context: Context,
    private val onButtonClickListener: (room: Room) -> Unit,
    private val hotelAdapter: HotelAdapter
) : ListAdapter<Room, RoomAdapter.RoomViewHolder>(DiffCallBack) {


    inner class RoomViewHolder(val binding: RoomItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Room, onButtonClickListener: (room: Room) -> Unit) {

            with(binding) {
                roomsImageRecyclerView.adapter = hotelAdapter
                roomsImageRecyclerView.layoutManager = CarouselLayoutManager()
                roomsImageRecyclerView.setHasFixedSize(true)
                hotelName.text = item.name
                benefit1.text = item.peculiarities[0]
                benefit2.text = item.peculiarities[1]
                if (item.peculiarities.size == 3) {
                    benefit3.visibility = View.VISIBLE
                    benefit3.text = item.peculiarities[2]
                } else {
                    benefit3.visibility = View.GONE
                }
                tourPrice.text = context.getString(R.string.room_price, item.price)
                tourPriceDescription.text = item.priceDescription

                buttonToRooms.setOnClickListener {
                    onButtonClickListener(item)
                }
            }

            hotelAdapter.submitList(item.imageUrls)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomAdapter.RoomViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RoomItemBinding.inflate(inflater, parent, false)

        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomAdapter.RoomViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onButtonClickListener)
    }

    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<Room>() {

            override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
                return oldItem == newItem
            }
        }

    }
}