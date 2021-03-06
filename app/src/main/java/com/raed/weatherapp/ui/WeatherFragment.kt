package com.raed.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.raed.weatherapp.R
import com.raed.weatherapp.databinding.FragmentWeatherDetailBinding
import com.raed.weatherapp.model.City
import com.raed.weatherapp.model.WeatherResponse
import com.raed.weatherapp.utils.NetworkViewState
import com.raed.weatherapp.utils.getTime
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat


/**
 * Created By Raed Saeed on 02/04/2022
 */

@AndroidEntryPoint
class WeatherFragment : Fragment() {
    private lateinit var binding: FragmentWeatherDetailBinding
    private val weatherViewModel: WeatherViewModel by viewModels()

    companion object {
        const val WEATHER = "com.detailFragment.WEATHER"
        const val CITY_NAME = "com.detailFragment.CITY_NAME"
        const val IMAGE_URL = "http://openweathermap.org/img/w/"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWeatherDetailBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getParcelable<WeatherResponse>(WEATHER)?.let {
            populateUI(it)
        }

        arguments?.getParcelable<City>(CITY_NAME)?.let {
            fetchWeatherDetails(it)
        }

        weatherViewModel.weatherLiveData.observe(viewLifecycleOwner) { populateUI(it) }
    }

    private fun fetchWeatherDetails(city: City) {
        weatherViewModel.getWeatherInfo(city)
    }


    private fun populateUI(viewState: NetworkViewState) {
        when (viewState) {
            is NetworkViewState.Loading -> {
                binding.pbFragmentWeatherDetailsLoading.isVisible = true
            }

            is NetworkViewState.Success<*> -> {
                binding.pbFragmentWeatherDetailsLoading.isVisible = false
                binding.cvFragmentWeatherDetailCard.isVisible = true
                populateUI(viewState.item as? WeatherResponse)
            }

            is NetworkViewState.Error -> {
                binding.pbFragmentWeatherDetailsLoading.isVisible = false
                Snackbar.make(
                    binding.root,
                    viewState.error.message.toString(),
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun populateUI(weather: WeatherResponse?) {
        binding.cvFragmentWeatherDetailCard.isVisible = weather != null
        binding.pbFragmentWeatherDetailsLoading.isVisible = weather == null

        weather?.let {
            val cityName = "${it.name}, ${it.sys?.country}"

            Glide.with(this)
                .load("${IMAGE_URL}${it.weather?.get(0)?.icon}.png")
                .into(binding.ivTvFragmentWeatherImage)

            binding.tvFragmentWeatherCity.text = cityName
            binding.tvFragmentWeatherDesc.text = it.weather?.get(0)?.description
            binding.tvFragmentWeatherTemp.text =
                getString(R.string.degree_celsius, (it.main?.temp!! - 273.15).toInt())
            binding.tvFragmentWeatherHum.text =
                getString(R.string.humidity_percentage, it.main.humidity)
            binding.tvFragmentWeatherWind.text = getString(
                R.string.wind_km,
                DecimalFormat("#.#").format(it.wind?.speed)
            )
            binding.tvFragmentWeatherMessage.text =
                getString(R.string.weather_info, cityName, it.dt.getTime())
        }
    }
}