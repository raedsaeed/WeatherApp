package com.raed.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.raed.weatherapp.R
import com.raed.weatherapp.databinding.DialogAddCityBinding
import com.raed.weatherapp.model.City
import dagger.hilt.android.AndroidEntryPoint
import java.util.*


/**
 * Created By Raed Saeed on 01/04/2022
 */

@AndroidEntryPoint
class AddCityDialog : BottomSheetDialogFragment() {
    private lateinit var binding: DialogAddCityBinding
    private val cityViewModel: CityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(layoutInflater, R.layout.dialog_add_city, container, false)

        binding.etDialogAddCityText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                saveCity()
            }
            return@setOnEditorActionListener true
        }

        binding.btnCreateCity.setOnClickListener {
            saveCity()
        }

        return binding.root
    }

    private fun saveCity() {
        if (!binding.etDialogAddCityText.text.isNullOrEmpty()) {
            val data = binding.etDialogAddCityText.text.toString()
            val entities = data.split(",")
            val city = entities[0].replace(" ", "")
                .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            val newCity: City = if (entities.size > 1) {
                City(city, entities[1].replace(" ", ""))
            } else City(city = city)

            cityViewModel.addNewCity(newCity)
            dismiss()
        } else {
            Toast.makeText(requireContext(), "Add City Name", Toast.LENGTH_SHORT).show()
        }
    }
}