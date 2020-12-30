package com.katsidzira.cloudcover.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.katsidzira.cloudcover.databinding.ListViewForecastBinding
import com.katsidzira.cloudcover.models.ForecastDay

class ForecastAdapter(private val forecastDays: List<ForecastDay>): RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListViewForecastBinding.inflate(inflater)
        return ForecastViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ForecastViewHolder, position: Int) {
        holder.bind(forecastDays[position])
    }

    override fun getItemCount() = forecastDays.size

    inner class ForecastViewHolder(private val binding: ListViewForecastBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(forecastDay: ForecastDay) {
            binding.forecastDay = forecastDay
        }
    }
}