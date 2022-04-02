package com.raed.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.raed.weatherapp.R
import com.raed.weatherapp.databinding.FragmentCitiesBinding
import com.raed.weatherapp.defaultNavOptions
import com.raed.weatherapp.model.City
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

        val cityAdapter = CityAdapter()
        cityAdapter.setOnCityClickListener(object : CityAdapter.OnCityClick {
            override fun onInfoClick(city: City) {
                findNavController().graph.findNode(R.id.weather_fragment)?.label = city.getName()
                findNavController().navigate(
                    R.id.weather_fragment, bundleOf(
                        WeatherFragment.CITY_NAME to city
                    ),
                    defaultNavOptions
                )
            }

            override fun onDetailClick(city: City) {
                findNavController().graph.findNode(R.id.weather_history_fragment)?.label =
                    getString(R.string.historical_title, city.city)
                findNavController().navigate(
                    R.id.weather_history_fragment, bundleOf(
                        HistoricalWeatherFragment.CITY to city
                    ),
                    defaultNavOptions
                )
            }
        })

        binding.rvFragmentCitiesList.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFragmentCitiesList.adapter = cityAdapter

        cityViewModel.getCities()
        cityViewModel.citiesLiveData.observe(viewLifecycleOwner) {
            cityAdapter.setItems(it.toMutableList())
        }

        return binding.root
    }
}