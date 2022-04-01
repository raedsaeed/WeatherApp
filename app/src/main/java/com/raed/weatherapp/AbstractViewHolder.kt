package com.raed.weatherapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView


/**
 * Created By Raed Saeed on 01/04/2022
 */
abstract class AbstractViewHolder<BaseObject>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: BaseObject)
}