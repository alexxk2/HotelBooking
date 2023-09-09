package com.example.hotelbooking.presentation.hotel.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hotelbooking.R
import com.example.hotelbooking.databinding.FragmentHotelBinding
import com.example.hotelbooking.domain.models.Hotel
import com.example.hotelbooking.presentation.hotel.view_model.HotelViewModel
import com.example.hotelbooking.presentation.hotel.view_model.ScreenState
import com.google.android.material.carousel.CarouselLayoutManager
import com.google.android.material.carousel.CarouselStrategy
import com.google.android.material.carousel.MultiBrowseCarouselStrategy
import org.koin.androidx.viewmodel.ext.android.viewModel


class HotelFragment : Fragment() {

    private var _binding: FragmentHotelBinding? = null
    private val binding get() = _binding!!
    private val viewModel: HotelViewModel by viewModel()
    private lateinit var hotelAdapter: HotelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHotelBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        viewModel.getHotelInfo()

        viewModel.hotel.observe(viewLifecycleOwner) { hotel ->
            bindViews(hotel)
            hotelAdapter.submitList(hotel.imageUrls)
        }

        viewModel.screenState.observe(viewLifecycleOwner) { screenState ->
            manageScreenContent(screenState)
        }

    }

    private fun bindViews(hotel: Hotel) {

        with(binding) {
            ratingText.text = getString(R.string.rating_message, hotel.rating, hotel.ratingName)
            hotelName.text = hotel.name
            hotelAddress.text = hotel.address
            tourPrice.text = getString(R.string.tour_price, hotel.price)
            tourPriceDescription.text = hotel.priceDescription
            benefit1.text = hotel.peculiarities[0]
            benefit2.text = hotel.peculiarities[1]
            benefit3.text = hotel.peculiarities[2]
            benefit4.text = hotel.peculiarities[3]
            longHotelDescription.text = hotel.description
        }
    }

    private fun manageScreenContent(screenState: ScreenState) {

        with(binding) {
            when (screenState) {

                ScreenState.Done -> {
                    basicInfoLayout.visibility = View.VISIBLE
                    detailsInfoLayout.visibility = View.VISIBLE
                    bottomCardView.visibility = View.VISIBLE
                    errorLayout.visibility = View.GONE
                    progressBar.visibility = View.GONE

                }

                ScreenState.Error -> {
                    basicInfoLayout.visibility = View.GONE
                    detailsInfoLayout.visibility = View.GONE
                    bottomCardView.visibility = View.GONE
                    errorLayout.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE

                }

                ScreenState.Loading -> {
                    basicInfoLayout.visibility = View.GONE
                    detailsInfoLayout.visibility = View.GONE
                    bottomCardView.visibility = View.GONE
                    errorLayout.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE

                }
            }
        }
    }

    private fun setRecyclerView(){
        hotelAdapter = HotelAdapter()
        binding.imageRecyclerView.adapter = hotelAdapter
        binding.imageRecyclerView.layoutManager = CarouselLayoutManager()
        binding.imageRecyclerView.setHasFixedSize(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}