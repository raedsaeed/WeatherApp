package com.raed.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.raed.weatherapp.R
import com.raed.weatherapp.databinding.FragmentWeatherHistoryBinding
import com.raed.weatherapp.model.BaseObject
import com.raed.weatherapp.model.City
import com.raed.weatherapp.ui.WeatherFragment.Companion.WEATHER
import com.raed.weatherapp.utils.NetworkViewState
import com.raed.weatherapp.utils.defaultNavOptions
import dagger.hilt.android.AndroidEntryPoint


/**
 * Created By Raed Saeed on 02/04/2022
 */
@AndroidEntryPoint
class HistoricalWeatherFragment : Fragment() {
    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var binding: FragmentWeatherHistoryBinding

    @Suppress("UNCHECKED_CAST")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherHistoryBinding.inflate(inflater, container, false)

        arguments?.getParcelable<City>(CITY)?.let {
            weatherViewModel.getHistoricalWeatherInfo(city = it)
        }

        val historyAdapter = WeatherHistoryAdapter()
        historyAdapter.setOnCityClickListener { response ->
            findNavController().graph.findNode(R.id.weather_fragment)?.label =
                "${response?.name}, ${response?.sys?.country}"

            findNavController().navigate(
                R.id.weather_fragment, bundleOf(
                    WEATHER to response
                ),
                defaultNavOptions
            )
        }
        binding.rvFragmentWeatherHistoryList.adapter = historyAdapter
        binding.rvFragmentWeatherHistoryList.layoutManager = LinearLayoutManager(requireContext())

        weatherViewModel.weatherLiveData.observe(viewLifecycleOwner) {
            if (it is NetworkViewState.Success<*>) {
                historyAdapter.setItems(it.item as? List<BaseObject>)
            }
        }

        return binding.root
    }

    companion object {
        const val CITY = "com.HistoricalWeatherFragment.CITY"
    }
}