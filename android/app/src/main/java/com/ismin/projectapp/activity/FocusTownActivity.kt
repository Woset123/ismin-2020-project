package com.ismin.projectapp.activity

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.ismin.projectapp.R
import kotlinx.android.synthetic.main.activity_focus_town.*
import kotlinx.android.synthetic.main.activity_main.*

class FocusTownActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_focus_town)

        val intent = intent

        val Country = intent.getStringExtra("country")
        val City = intent.getStringExtra("city")
        val Population = intent.getStringExtra("population")

        val seeCity = this.findViewById<TextView>(R.id.city_name)
        val seePopulation = this.findViewById<TextView>(R.id.population_value)
        val seeCountry = this.findViewById<TextView>(R.id.country_name)

        seeCity.text = City
        seeCountry.text = Country
        seePopulation.text = Population

    }

}
