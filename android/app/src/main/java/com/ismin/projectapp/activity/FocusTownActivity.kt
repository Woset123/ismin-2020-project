package com.ismin.projectapp.activity

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import com.ismin.projectapp.R
import com.ismin.projectapp.Town
import com.ismin.projectapp.TownList
import com.ismin.projectapp.retrofit.IRequests
import kotlinx.android.synthetic.main.activity_focus_town.*
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FocusTownActivity : AppCompatActivity() {

    private lateinit var irequests: IRequests

    val favTown = TownList()
    private lateinit var City: String
    private lateinit var Country: String
    private lateinit var Population: String

    private val TownActivityCode = 1;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_focus_town)

        val intent = intent

        City = intent.getStringExtra("city")
        Country = intent.getStringExtra("country")
        Population = intent.getStringExtra("population")

        val seeCity = this.findViewById<TextView>(R.id.city_name)
        val seePopulation = this.findViewById<TextView>(R.id.population_value)
        val seeCountry = this.findViewById<TextView>(R.id.country_name)

        seeCity.text = City
        seeCountry.text = Country
        seePopulation.text = Population

        //Retrofit
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://towns-app.cleverapps.io")
            .build()

        irequests = retrofit.create(IRequests::class.java)


        irequests.allFavorites().enqueue(object : Callback<ArrayList<Town>> {
            override fun onResponse(
                call: Call<ArrayList<Town>>,
                response: Response<ArrayList<Town>>
            ) {
                val alltowns = response.body()
                alltowns?.forEach{
                    favTown.addTown(it)
                }
            }

            override fun onFailure(call: Call<ArrayList<Town>>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Network error ${t.localizedMessage}",
                    Toast.LENGTH_LONG
                ).show()
            }
        })

        // Listener when Click on button to Add a new town
        val button = this.findViewById<Button>(R.id.btn_fav)
        button.setOnClickListener { view -> addToFavorites(view)}

    }

    fun addToFavorites(view : View) {

        var town = Town(City, Country, Population)

        if (favTown.exist(town)) {
            btn_fav.setBackgroundResource(R.drawable.ic_baseline_favorite_shadow)
            Toast.makeText(this, "Removed from Favorites", Toast.LENGTH_LONG).show()
            //Remove from Favorites
            irequests.removeFromFavorite(town.City).enqueue(object : Callback<String> {
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Network error ${t.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }

                @RequiresApi(Build.VERSION_CODES.N)
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    favTown.removeTown(response.body()!!)
                    back(view)
                }

            })

        }
        else {
            btn_fav.setBackgroundResource(R.drawable.ic_baseline_favorite_red)
            Toast.makeText(this, "Added to Favorites", Toast.LENGTH_LONG).show()
            //Add to Favorites
            irequests.addToFavorite(town).enqueue(object : Callback<Town> {
                override fun onFailure(call: Call<Town>, t: Throwable) {
                    Toast.makeText(
                        applicationContext,
                        "Network error ${t.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }

                override fun onResponse(call: Call<Town>, response: Response<Town>) {
                    favTown.addTown(response.body()!!)
                    back(view)
                }

            })

        }
    }

    fun back(view: View) {
        //Return to the TownListActivity
        val intent = Intent(this, TownListActivity::class.java)
        startActivityForResult(intent, this.TownActivityCode)
    }
}
