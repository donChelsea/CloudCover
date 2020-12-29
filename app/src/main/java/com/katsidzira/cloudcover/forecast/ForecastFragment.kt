package com.katsidzira.cloudcover.forecast

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.katsidzira.cloudcover.databinding.FragmentForecastBinding

class ForecastFragment : Fragment() {

    private lateinit var binding: FragmentForecastBinding
    private lateinit var model: ForecastViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater)

        model = ViewModelProvider(this).get(ForecastViewModel::class.java)

        model.getForecast("90210", 3)

        model.forecastData.observe(viewLifecycleOwner, Observer { weather ->
            Log.d("ForecastFragment", "forecast days: ${weather.forecast.forecastDay.size}")
        })

        return binding.root
    }

}