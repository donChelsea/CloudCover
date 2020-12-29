package com.katsidzira.cloudcover.current

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.katsidzira.cloudcover.R
import com.katsidzira.cloudcover.databinding.FragmentCurrentBinding

class CurrentFragment : Fragment() {

    private lateinit var binding: FragmentCurrentBinding
    private lateinit var model: CurrentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCurrentBinding.inflate(inflater)

        model = ViewModelProvider(this).get(CurrentViewModel::class.java)

        model.getCurrentWeather("90210")

        model.weatherData.observe(viewLifecycleOwner, Observer { weather ->
            Log.d("CurrentFragment", weather.location.name)
            Log.d("CurrentFragment", weather.current.windSpeed)
        })

        binding.button.setOnClickListener {
            findNavController().navigate(R.id.action_currentWeatherFragment_to_forecastFragment)
        }

        return binding.root
    }

}