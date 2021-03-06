package com.ismin.projectapp.activity


import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
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

    val townlist = TownList()
    val favTown = TownList()

    private lateinit var fragmentAdapter : PagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_town_list)

        toolbar.setTitle("Population Cities")
        setSupportActionBar(toolbar)

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
                displayErrorToast(t)
            }
        })


        irequests.allTowns().enqueue(object : Callback<ArrayList<Town>> {
            override fun onResponse(
                    call: Call<ArrayList<Town>>,
                    response: Response<ArrayList<Town>>
            ) {
                val alltowns = response.body()
                alltowns?.forEach{
                    townlist.addTown(it)
                }

                displayList()

            }

            override fun onFailure(call: Call<ArrayList<Town>>, t: Throwable) {
                displayErrorToast(t)
            }
        })

        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    displaySearch(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }


        })


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


    fun displayList() {

        fragmentAdapter = MyPagerAdapter(supportFragmentManager, townlist.getAllTowns(), favTown)
        viewpager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewpager)
    }

    //Search based On City name -> Not yet functional !!!
    fun displaySearch(string: String) {


        Log.v("123","Search")
        fragmentAdapter = MyPagerAdapter(supportFragmentManager, townlist.search(string), favTown)
        viewpager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewpager)
        fragmentAdapter.notifyDataSetChanged()

    }

    fun refresh(view: View) {

        finish()
        startActivity(intent)

    }


    fun removeItem(town: Town) {

        Toast.makeText(this,"Removed !", Toast.LENGTH_LONG).show()

        irequests.removeTown(town.City).enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>, t: Throwable) {
                /**Toast.makeText(
                        applicationContext,
                        "Network error ${t.localizedMessage}",
                        Toast.LENGTH_LONG
                ).show()**/
            }

            @RequiresApi(Build.VERSION_CODES.N)
            override fun onResponse(call: Call<String>, response: Response<String>) {
                townlist.removeTown(response.body()!!)
                favTown.removeTown(response.body()!!)
            }

        })

        finish()
        startActivity(intent)

    }
}