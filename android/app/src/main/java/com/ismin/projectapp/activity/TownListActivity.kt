package com.ismin.projectapp.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager.widget.PagerAdapter
import com.ismin.projectapp.R
import com.ismin.projectapp.Town
import com.ismin.projectapp.TownList
import com.ismin.projectapp.adapter.MyPagerAdapter
import com.ismin.projectapp.fragment.TownListFragment
import com.ismin.projectapp.retrofit.IRequests
import kotlinx.android.synthetic.main.activity_town_list.*
import kotlinx.android.synthetic.main.fragment_town_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TownListActivity : AppCompatActivity() {

    private val CreateTownActivityRequestCode = 1;
    private lateinit var irequests: IRequests

    private val city_test = Town(
            Country = "France",
            City = "Paris",
            Population = "2148000"
    )

    private val city_test2 = Town(
            Country = "France",
            City = "Tours",
            Population = "120000"
    )
    var townlist = TownList()

    private lateinit var fragmentAdapter : PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_town_list)

        toolbar.setTitle("Population Cities")
        setSupportActionBar(toolbar)


        townlist.addTown(city_test)
        townlist.addTown(city_test2)

        //Retrofit
        val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://towns-app.cleverapps.io")
                .build()

        irequests = retrofit.create(IRequests::class.java)

        irequests.allTowns().enqueue(object : Callback<ArrayList<Town>> {
            override fun onResponse(
                    call: Call<ArrayList<Town>>,
                    response: Response<ArrayList<Town>>
            ) {
                val alltowns = response.body()
                alltowns?.forEach{
                    townlist.addTown(it)
                }

            }

            override fun onFailure(call: Call<ArrayList<Town>>, t: Throwable) {
                displayErrorToast(t)
            }
        })

        fragmentAdapter = MyPagerAdapter(supportFragmentManager, townlist.getAllTowns())
        viewpager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewpager)


    }

    private fun displayErrorToast(t: Throwable) {
        Toast.makeText(
                applicationContext,
                "Network error ${t.localizedMessage}",
                Toast.LENGTH_LONG
        ).show()
    }


     fun createNewTown(view: View) {
        val intent = Intent(this, CreateTownActivity::class.java)
        startActivityForResult(intent, this.CreateTownActivityRequestCode)
    }


}