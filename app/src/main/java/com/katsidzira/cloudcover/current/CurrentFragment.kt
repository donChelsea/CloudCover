package com.katsidzira.cloudcover.current

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.katsidzira.cloudcover.databinding.FragmentCurrentBinding
import com.squareup.picasso.Picasso

class CurrentFragment : Fragment() {

    private lateinit var binding: FragmentCurrentBinding
    private lateinit var model: CurrentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentBinding.inflate(inflater)

        model = ViewModelProvider(this).get(CurrentViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etZipcode.doAfterTextChanged {
            val zip = it.toString()
            if (zip.length >= 5) model.enterZipCode(zip)
        }

        model.zipCode.observe(viewLifecycleOwner, Observer { zip ->
            model.getCurrentWeather(zip)
        })

        model.weatherData.observe(viewLifecycleOwner, Observer { weather ->
            binding.tvDate.text = weather.location.datestamp
            binding.tvLocation.text = weather.location.name
            binding.tvDegrees.text = "${weather.current.temp}°"
            binding.tvCondition.text = weather.current.condition.text
            binding.tvHumidity.text = "Humidity: ${weather.current.humidity}%"
            binding.tvFeelsLike.text = "Feels like: ${weather.current.feelsLike}°"

            Log.d("CurrentFragment", weather.location.name)
            Log.d("CurrentFragment", weather.current.windSpeed)
        })
    }

}