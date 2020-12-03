package com.ismin.projectapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import com.ismin.projectapp.R
import com.ismin.projectapp.Town
import com.ismin.projectapp.retrofit.IRequests
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CreateTownActivity() : AppCompatActivity() {


    private lateinit var edtCity: EditText
    private lateinit var edtPopulation: EditText
    private lateinit var edtCountry: EditText
    private lateinit var card: CardView
    private lateinit var rootLayout: ViewGroup
    private val CreateTownActivityCode = 1;

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_town)


        rootLayout = this.findViewById(R.id.create_town_root_lyt)
        edtCity = this.findViewById(R.id.f_create_town_edt_name)
        edtPopulation = this.findViewById(R.id.f_create_town_edt_pop)
        edtCountry = this.findViewById(R.id.f_create_town_edt_country)
        card = this.findViewById(R.id.f_create_town_card)

        // Listener when Click on button to Add a new town
        val button = this.findViewById<AppCompatButton>(R.id.f_create_town_btn_save)
        button.setOnClickListener { view -> saveTown(view)}

    }

    fun saveTown(view : View) {
        var town = Town(
                    edtCity.text.toString(),
                    edtPopulation.text.toString(),
                    edtCountry.text.toString()
                )


        //Retrofit
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://localhost:3001")
                .build()

        var irequests = retrofit.create(IRequests::class.java)

        irequests.createTown(town)


        //Return to the TownListActivity
        val intent = Intent(this, TownListActivity::class.java)
        startActivityForResult(intent, this.CreateTownActivityCode)
    }
}