package com.raed.weatherapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.raed.weatherapp.R
import com.raed.weatherapp.databinding.ItemCityBinding
import com.raed.weatherapp.model.AbstractViewHolder
import com.raed.weatherapp.model.BaseObject
import com.raed.weatherapp.model.City


/**
 * Created By Raed Saeed on 02/04/2022
 */
class CityAdapter : RecyclerView.Adapter<AbstractViewHolder<BaseObject>>() {
    private var items: MutableList<BaseObject>? = null
    private var onCityClickListener: OnCityClick? = null


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractViewHolder<BaseObject> {
        val viewBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_city,
            parent,
            false
        )

        return CityViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: AbstractViewHolder<BaseObject>, position: Int) {
        items?.get(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    fun setItems(cities: MutableList<BaseObject>) {
        this.items = cities
        notifyDataSetChanged()
    }

    fun clear() {
        items?.clear()
    }

    interface OnCityClick {
        fun onInfoClick(city: City)
        fun onDetailClick(city: City)
    }

    fun setOnCityClickListener(listener: OnCityClick) {
        this.onCityClickListener = listener
    }

    inner class CityViewHolder(viewBinding: ViewDataBinding) :
        AbstractViewHolder<BaseObject>(viewBinding.root), View.OnClickListener {
        private val binding: ItemCityBinding = viewBinding as ItemCityBinding

        init {
            binding.ivItemCityIcon.setOnClickListener(this)
            binding.ivItemCityInfo.setOnClickListener(this)
            binding.tvItemCityName.setOnClickListener(this)
        }

        override fun bind(item: BaseObject) {
            val city = item as City
            val text = if (city.country.isNotEmpty()) {
                StringBuilder(city.city).append(", ").append(city.country)
            } else {
                city.city
            }

            binding.tvItemCityName.text = text
        }

        override fun onClick(v: View?) {
            if (adapterPosition != -1) {
                val city = items?.get(adapterPosition) as? City
                if (v?.id == R.id.iv_item_city_info) {
                    onCityClickListener?.onInfoClick(city!!)
                } else {
                    onCityClickListener?.onDetailClick(city!!)
                }
            }
        }
    }

}