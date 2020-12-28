package com.katsidzira.cloudcover

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.katsidzira.cloudcover.network.WeatherService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}