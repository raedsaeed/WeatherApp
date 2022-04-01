package com.raed.weatherapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.raed.weatherapp.R
import com.raed.weatherapp.databinding.FragmentCitiesBinding
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created By Raed Saeed on 01/04/2022
 */
@AndroidEntryPoint
class CityFragment : Fragment() {
    private lateinit var binding: FragmentCitiesBinding
    private val cityViewModel: CityViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cities, container, false)
        binding.btnAddCity.setOnClickListener {
            val addCitiesBinding = AddCityDialog()
            addCitiesBinding.show(childFragmentManager, tag)
        }

        cityViewModel.getCities()

        cityViewModel.citiesLiveData.observe(viewLifecycleOwner) {
            Log.e("TAG", "onCreateView: size  ${it?.size}")
        }
        return binding.root
    }
}