package com.example.weatherapplication2.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapplication2.R
import com.example.weatherapplication2.databinding.RowItemWeatherBinding
import com.example.weatherapplication2.domain.models.Weather

class WeatherAdapter(
    private val weatherList: List<Weather>,
    private val onClick: (Weather) -> Unit
) : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            RowItemWeatherBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).also { weatherViewHolder ->
            weatherViewHolder.itemView.setOnClickListener {
                onClick(weatherList[weatherViewHolder.adapterPosition])
            }
        }
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.bind(weatherList[position])
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    class WeatherViewHolder(private val binding: RowItemWeatherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(weather: Weather) = with(binding) {
            descriptionTv.text = weather.description
            tempTv.text = root.context.getString(R.string.temp).format(weather.temp.toString())
        }
    }
}