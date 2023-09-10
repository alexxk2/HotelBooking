package com.example.hotelbooking.presentation.booking.ui

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelbooking.R
import com.example.hotelbooking.databinding.TouristItemBinding
import com.example.hotelbooking.domain.models.TouristInfo
import com.google.android.material.textfield.TextInputEditText

class TouristAdapter(
    private val context: Context,
    private val onShowClickListener: (touristInfo: TouristInfo) -> Unit,
    private val onHideClickListener: (touristInfo: TouristInfo) -> Unit,
) : ListAdapter<TouristInfo, TouristAdapter.TouristViewHolder>(DiffCallBack) {


    inner class TouristViewHolder(val binding: TouristItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            item: TouristInfo,
            onShowClickListener: (touristInfo: TouristInfo) -> Unit,
            onHideClickListener: (touristInfo: TouristInfo) -> Unit
        ) {
            addEmptyFieldListener(binding.birthdayInputEditText)
            addEmptyFieldListener(binding.citizenshipInputEditText)
            addEmptyFieldListener(binding.firstNameInputEditText)
            addEmptyFieldListener(binding.lastNameInputEditText)
            addEmptyFieldListener(binding.passportInputEditText)
            addEmptyFieldListener(binding.passportExpiringInputEditText)

            binding.touristInfoTitle.text =
                context.getString(R.string.tourist_info_title, drawTitle(item.id))
            manageContent(item.isVisible)

            binding.showButton.setOnClickListener { onShowClickListener(item) }
            binding.hideButton.setOnClickListener { onHideClickListener(item) }

        }

        private fun manageContent(isVisible: Boolean) {
            with(binding) {
                if (isVisible) {
                    hideButton.visibility = View.VISIBLE
                    showButton.visibility = View.GONE
                    firstNameInputLayout.visibility = View.VISIBLE
                    lastNameInputLayout.visibility = View.VISIBLE
                    birthdayInputLayout.visibility = View.VISIBLE
                    citizenshipInputLayout.visibility = View.VISIBLE
                    passportInputLayout.visibility = View.VISIBLE
                    passportExpiringInputLayout.visibility = View.VISIBLE
                } else {
                    hideButton.visibility = View.GONE
                    showButton.visibility = View.VISIBLE
                    firstNameInputLayout.visibility = View.GONE
                    lastNameInputLayout.visibility = View.GONE
                    birthdayInputLayout.visibility = View.GONE
                    citizenshipInputLayout.visibility = View.GONE
                    passportInputLayout.visibility = View.GONE
                    passportExpiringInputLayout.visibility = View.GONE
                }
            }
        }

        private fun drawTitle(id: Int): String {
            return when (id) {
                1 -> "Первый"
                2 -> "Второй"
                3 -> "Третий"
                4 -> "Четвертый"
                5 -> "Пятый"
                6 -> "Шестой"
                7 -> "Седьмой"
                8 -> "Восьмой"
                9 -> "Девятый"
                10 -> "Десятый"
                else -> "$id"
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TouristViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TouristItemBinding.inflate(inflater, parent, false)

        return TouristViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TouristViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(
            item = item,
            onHideClickListener = onHideClickListener,
            onShowClickListener = onShowClickListener
        )
    }

    companion object {
        val DiffCallBack = object : DiffUtil.ItemCallback<TouristInfo>() {

            override fun areItemsTheSame(oldItem: TouristInfo, newItem: TouristInfo): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: TouristInfo, newItem: TouristInfo): Boolean {
                return oldItem == newItem
            }
        }
    }

    private fun addEmptyFieldListener(view: TextInputEditText) {
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrBlank()) {
                    view.background =
                        context.resources.getDrawable(R.drawable.edit_text_error_background)
                } else {
                    view.background =
                        context.resources.getDrawable(R.drawable.edit_text_background)
                }
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }
}