package com.katsidzira.cloudcover.forecast

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.katsidzira.cloudcover.R
import com.katsidzira.cloudcover.databinding.FragmentForecastBinding

class ForecastFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: FragmentForecastBinding
    private lateinit var model: ForecastViewModel
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForecastBinding.inflate(inflater)

        model = ViewModelProvider(this).get(ForecastViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.days_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinner.adapter = adapter
        }

        binding.spinner.setSelection(4)
        binding.spinner.prompt = "Days"
        binding.spinner.onItemSelectedListener = this

        sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        val zipCode = sharedPref.getString("ZIPCODE", "")
        val days = sharedPref.getInt("DAYS", model.days)

        binding.etForecastZipcode.doAfterTextChanged {
            val zip = it.toString()
            if (zipCode.isNullOrBlank() && zip.length >= 5) {
                model.getForecast(zip, days)
            }
        }

        model.forecastData.observe(viewLifecycleOwner, Observer { weather ->
        })
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, pos: Int, long: Long) {
        val days = parent?.getItemAtPosition(pos).toString().toInt()
        model.days = days
        with (sharedPref.edit()) {
            putInt("DAYS", days)
            apply()
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }

}