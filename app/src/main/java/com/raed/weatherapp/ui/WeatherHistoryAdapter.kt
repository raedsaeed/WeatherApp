package com.raed.weatherapp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.raed.weatherapp.R
import com.raed.weatherapp.databinding.ItemWeatherHistoryBinding
import com.raed.weatherapp.model.AbstractViewHolder
import com.raed.weatherapp.model.BaseObject
import com.raed.weatherapp.model.WeatherResponse
import com.raed.weatherapp.utils.getTime


/**
 * Created By Raed Saeed on 02/04/2022
 */
class WeatherHistoryAdapter : RecyclerView.Adapter<AbstractViewHolder<BaseObject>>() {
    private var items: List<BaseObject>? = null
    private var onCityClickListener: OnWeatherClickListener? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractViewHolder<BaseObject> {
        val viewBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_weather_history,
            parent,
            false
        )

        return WeatherHistoryViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: AbstractViewHolder<BaseObject>, position: Int) {
        items?.get(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(cities: List<BaseObject>?) {
        this.items = cities
        notifyDataSetChanged()
    }

    fun interface OnWeatherClickListener {
        fun onDetailClick(weather: WeatherResponse?)
    }

    fun setOnCityClickListener(listener: OnWeatherClickListener) {
        this.onCityClickListener = listener
    }

    inner class WeatherHistoryViewHolder(viewBinding: ViewDataBinding) :
        AbstractViewHolder<BaseObject>(viewBinding.root), View.OnClickListener {
        private val binding: ItemWeatherHistoryBinding = viewBinding as ItemWeatherHistoryBinding

        init {
            binding.clItemWeatherHistoryContainer.setOnClickListener(this)
        }

        override fun bind(item: BaseObject) {
            val element = item as WeatherResponse
            Glide.with(itemView)
                .load("${IMAGE_URL}${element.weather?.get(0)?.icon}.png")
                .into(binding.ivItemWeatherHistoryImage)

            binding.tvItemWeatherHistoryDate.text = element.dt.getTime()
            val degree = itemView.context?.getString(
                R.string.degree_celsius,
                ((element.main?.temp!! - 273.15).toInt())
            )
            val desc = "${element.weather?.get(0)?.description}, $degree"
            binding.tvItemWeatherHistoryDesc.text = desc
        }

        override fun onClick(v: View?) {
            if (adapterPosition != -1) {
                val weather = items?.get(adapterPosition) as? WeatherResponse
                onCityClickListener?.onDetailClick(weather)
            }
        }
    }

    companion object {
        const val IMAGE_URL = "http://openweathermap.org/img/w/"
    }
}