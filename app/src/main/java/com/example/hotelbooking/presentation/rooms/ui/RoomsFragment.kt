package com.example.hotelbooking.presentation.rooms.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hotelbooking.databinding.FragmentRoomsBinding
import com.example.hotelbooking.presentation.hotel.ui.HotelAdapter
import com.example.hotelbooking.presentation.hotel.view_model.ScreenState
import com.example.hotelbooking.presentation.rooms.view_model.RoomsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class RoomsFragment : Fragment() {
    private var _binding: FragmentRoomsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: RoomsViewModel by viewModel()
    private lateinit var roomAdapter: RoomAdapter
    private lateinit var hotelAdapter: HotelAdapter
    private var hotelName = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            hotelName = it.getString(HOTEL_NAME)!!
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            navigateBack()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomsBinding.inflate(layoutInflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
        viewModel.getRoomsInfo()

        viewModel.room.observe(viewLifecycleOwner) { listOfRooms ->
            roomAdapter.submitList(listOfRooms)

        }
        viewModel.screenState.observe(viewLifecycleOwner) { screenState ->
            manageScreenContent(screenState)
        }

        binding.topAppBar.title = hotelName

        binding.topAppBar.setNavigationOnClickListener { navigateBack() }

    }

    private fun manageScreenContent(screenState: ScreenState) {

        with(binding) {
            when (screenState) {

                ScreenState.Done -> {
                    roomsRecyclerView.visibility = View.VISIBLE
                    errorLayout.visibility = View.GONE
                    progressBar.visibility = View.GONE
                }

                ScreenState.Error -> {
                    roomsRecyclerView.visibility = View.GONE
                    errorLayout.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }

                ScreenState.Loading -> {
                    roomsRecyclerView.visibility = View.GONE
                    errorLayout.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setRecyclerView() {
        hotelAdapter = HotelAdapter()
        roomAdapter = RoomAdapter(
            context = requireContext(),
            hotelAdapter = hotelAdapter,
            onButtonClickListener = { room ->
                val action = RoomsFragmentDirections.actionRoomsFragmentToBookingFragment()
                findNavController().navigate(action)
            })
        binding.roomsRecyclerView.adapter = roomAdapter
        binding.roomsRecyclerView.setHasFixedSize(true)

    }

    private fun navigateBack(){
        val action = RoomsFragmentDirections.actionRoomsFragmentToHotelFragment()
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val HOTEL_NAME = "hotelName"
    }
}