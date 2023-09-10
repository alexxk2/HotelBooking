package com.example.hotelbooking.presentation.booking.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hotelbooking.R
import com.example.hotelbooking.databinding.FragmentBookingBinding
import com.example.hotelbooking.domain.models.BookingInfo
import com.example.hotelbooking.domain.models.TouristInfo
import com.example.hotelbooking.presentation.booking.view_model.BookingViewModel
import com.example.hotelbooking.presentation.booking.view_model.ErrorState
import com.example.hotelbooking.presentation.hotel.view_model.ScreenState
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.tinkoff.decoro.Mask
import ru.tinkoff.decoro.MaskImpl
import ru.tinkoff.decoro.slots.PredefinedSlots
import ru.tinkoff.decoro.watchers.FormatWatcher
import ru.tinkoff.decoro.watchers.MaskFormatWatcher


class BookingFragment : Fragment() {

    private var _binding: FragmentBookingBinding? = null
    private val binding get() = _binding!!
    private lateinit var touristAdapter: TouristAdapter
    private val viewModel: BookingViewModel by viewModel()
    private val listOfTouristBlocks = mutableListOf<TouristInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBookingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()

        viewModel.getBookingInfo()

        viewModel.bookingInfo.observe(viewLifecycleOwner) { bookingInfo ->
            bindViews(bookingInfo)

            requireActivity().onBackPressedDispatcher.addCallback(this) {
                navigateBack()
            }

            binding.topAppBar.setNavigationOnClickListener { navigateBack() }
        }

        viewModel.screenState.observe(viewLifecycleOwner) { screenState ->
            manageScreenContent(screenState)
        }

        viewModel.errorState.observe(viewLifecycleOwner) { errorState ->
            manageErrorState(errorState)
        }

        binding.addTouristButton.setOnClickListener {

            touristAdapter.submitList(addAdditionalTouristInfoBlock())
            touristAdapter.notifyItemInserted(listOfTouristBlocks.size)
            binding.touristsRecyclerView.smoothScrollToPosition(listOfTouristBlocks.size - 1)
        }

        binding.buttonToOrder.setOnClickListener {

            viewModel.isInputValid(
                stringPhone = binding.phoneInputEditText.text,
                stringEmail = binding.emailInputEditText.text
            )

        }

        addEmptyFieldListener(binding.emailInputEditText)
        addEmptyFieldListener(binding.phoneInputEditText)

    }


    private fun bindViews(bookingInfo: BookingInfo) {

        setPhoneMaskOnEditText()

        with(binding) {
            ratingText.text =
                getString(R.string.rating_message, bookingInfo.rating, bookingInfo.ratingName)

            hotelName.text = bookingInfo.hotelName
            hotelAddress.text = bookingInfo.hotelAddress
            departurePlaceData.text = bookingInfo.departure
            countryCityData.text = bookingInfo.arrivalCountry

            datesData.text =
                getString(R.string.tour_dates, bookingInfo.tourDateStart, bookingInfo.tourDateEnd)

            numberOfNightsData.text =
                getString(R.string.number_of_nights_data, bookingInfo.numberOfNights)

            hotelTextData.text = bookingInfo.hotelName
            roomNameData.text = bookingInfo.roomType
            nutritionData.text = bookingInfo.nutrition
            tourPriceData.text = getString(R.string.simple_price, bookingInfo.tourPrice)
            fuelPriceData.text = getString(R.string.simple_price, bookingInfo.fuelCharge)
            servicePriceData.text = getString(R.string.simple_price, bookingInfo.serviceCharge)
            totalPriceData.text = getString(R.string.simple_price, bookingInfo.totalPrice)
            buttonToOrder.text = getString(R.string.pay_button_text, bookingInfo.totalPrice)

            touristAdapter.submitList(addFirstTouristInfoBlock())
        }
    }


    private fun manageScreenContent(screenState: ScreenState) {

        with(binding) {
            when (screenState) {

                ScreenState.Done -> {
                    basicInfoLayout.visibility = View.VISIBLE
                    bookingInfoLayout.visibility = View.VISIBLE
                    customerInfoLayout.visibility = View.VISIBLE
                    touristsRecyclerView.visibility = View.VISIBLE
                    addTouristLayout.visibility = View.VISIBLE
                    costsLayout.visibility = View.VISIBLE
                    bottomCardView.visibility = View.VISIBLE

                    errorLayout.visibility = View.GONE
                    progressBar.visibility = View.GONE

                }

                ScreenState.Error -> {
                    basicInfoLayout.visibility = View.GONE
                    bookingInfoLayout.visibility = View.GONE
                    customerInfoLayout.visibility = View.GONE
                    touristsRecyclerView.visibility = View.GONE
                    addTouristLayout.visibility = View.GONE
                    costsLayout.visibility = View.GONE
                    bottomCardView.visibility = View.GONE

                    errorLayout.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE

                }

                ScreenState.Loading -> {
                    basicInfoLayout.visibility = View.GONE
                    bookingInfoLayout.visibility = View.GONE
                    customerInfoLayout.visibility = View.GONE
                    touristsRecyclerView.visibility = View.GONE
                    addTouristLayout.visibility = View.GONE
                    costsLayout.visibility = View.GONE
                    bottomCardView.visibility = View.GONE

                    errorLayout.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setRecyclerView() {
        touristAdapter = TouristAdapter(requireContext(), onShowClickListener = { touristInfo ->
            touristAdapter.submitList(showInfoBlock(touristInfo.id))
            binding.touristsRecyclerView.smoothScrollToPosition(touristInfo.id - 1)
        }, onHideClickListener = { touristInfo ->
            touristAdapter.submitList(hideInfoBlock(touristInfo.id))
            binding.touristsRecyclerView.smoothScrollToPosition(touristInfo.id - 1)
        })
        binding.touristsRecyclerView.adapter = touristAdapter
        binding.touristsRecyclerView.setHasFixedSize(false)
    }

    private fun navigateBack() {
        val action = BookingFragmentDirections.actionBookingFragmentToRoomsFragment(
            viewModel.bookingInfo.value?.hotelName ?: "Отель"
        )
        findNavController().navigate(action)
    }

    private fun addFirstTouristInfoBlock(): List<TouristInfo> {

        val firstItem = TouristInfo(id = 1, isVisible = true)
        listOfTouristBlocks.add(firstItem)
        return listOfTouristBlocks
    }

    private fun addAdditionalTouristInfoBlock(): List<TouristInfo> {
        val helperList = mutableListOf<TouristInfo>()
        val newItem = TouristInfo(id = listOfTouristBlocks.size + 1, isVisible = false)
        listOfTouristBlocks.add(newItem)
        helperList.addAll(listOfTouristBlocks)
        return helperList
    }

    private fun showInfoBlock(id: Int): List<TouristInfo> {
        val tempList = mutableListOf<TouristInfo>()
        val newItem = TouristInfo(id = id, isVisible = true)
        listOfTouristBlocks[id - 1] = newItem

        tempList.addAll(listOfTouristBlocks)
        return tempList.toList()
    }

    private fun hideInfoBlock(id: Int): List<TouristInfo> {
        val tempList = mutableListOf<TouristInfo>()
        val newItem = TouristInfo(id = id, isVisible = false)
        listOfTouristBlocks[id - 1] = newItem

        tempList.addAll(listOfTouristBlocks)
        return tempList.toList()
    }

    private fun setPhoneMaskOnEditText() {
        val mask = MaskImpl.createTerminated(PredefinedSlots.RUS_PHONE_NUMBER)
        mask.isHideHardcodedHead = true
        val formatWatcher = MaskFormatWatcher(mask)
        formatWatcher.installOn(binding.phoneInputEditText)
    }


    private fun addEmptyFieldListener(view: TextInputEditText) {
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrBlank()) {
                    view.background =
                        resources.getDrawable(R.drawable.edit_text_error_background)
                } else {
                    view.background =
                        resources.getDrawable(R.drawable.edit_text_background)
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun manageErrorState(errorState: ErrorState) {

        when (errorState) {

            ErrorState.EmptyEmail -> {
                Snackbar.make(binding.emailInputEditText, "Empty mail", 3000).show()
                drawErrorAndScrollToIt(binding.emailInputEditText)
            }

            ErrorState.InvalidEmail -> {
                Snackbar.make(binding.emailInputEditText, "Invalid email", 3000).show()
                drawErrorAndScrollToIt(binding.emailInputEditText)
            }

            ErrorState.InvalidPhone -> {
                Snackbar.make(binding.emailInputEditText, "Invalid phone", 3000).show()
                drawErrorAndScrollToIt(binding.phoneInputEditText)
            }

            ErrorState.NotError -> {
                Snackbar.make(binding.emailInputEditText, "Everything is OK", 3000).show()
                //добавить навигацию к заказу
            }

            ErrorState.EmptyPhone -> {
                Snackbar.make(binding.emailInputEditText, "Empty phone", 3000).show()
                drawErrorAndScrollToIt(binding.phoneInputEditText)
            }

            ErrorState.BothEmpty -> {
                Snackbar.make(binding.emailInputEditText, "Both fields are empty", 3000).show()
                drawErrorAndScrollToIt(binding.phoneInputEditText)
                drawErrorAndScrollToIt(binding.emailInputEditText)
            }
        }
    }

    private fun drawErrorAndScrollToIt(view: TextInputEditText) {
        view.background = resources.getDrawable(R.drawable.edit_text_error_background)
        val coordinates = view.layoutParams
        binding.bookingScrollView.smoothScrollTo(coordinates.width, coordinates.height)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}