package com.example.hotelbooking.presentation.order.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.hotelbooking.R
import com.example.hotelbooking.databinding.FragmentOrderBinding
import com.example.hotelbooking.presentation.order.view_model.OrderViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class OrderFragment : Fragment() {

    private var _binding: FragmentOrderBinding? = null
    private val binding get() = _binding!!
    private val viewModel: OrderViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.randomOrder.observe(viewLifecycleOwner) { orderNumber ->
            binding.detailOrderInfo.text = getString(R.string.order_details,orderNumber)
        }

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.buttonToMain.setOnClickListener {
            val action = OrderFragmentDirections.actionOrderFragmentToHotelFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}