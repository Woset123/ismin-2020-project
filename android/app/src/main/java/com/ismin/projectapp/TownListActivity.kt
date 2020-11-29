package com.ismin.projectapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import com.ismin.projectapp.retrofit.IRequests
import kotlinx.android.synthetic.main.activity_town_list.*
import retrofit2.Retrofit

class TownListActivity : AppCompatActivity(), TownCreator {

    private val TAG = TownListActivity::class.simpleName

    private val townlist = TownList()

    private val city_test = Town(
            city = "Paris",
            population = 2148000,
            country = "France"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_town_list)

        //Call
        var retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build()

        var service = retrofit.create(IRequests::class.java)
        var repos = service.listTowns("paris") as Town


        this.townlist.addTown(repos)

        this.townlist.addTown(city_test)

        val townListFragment = TownListFragment.newInstance(townlist.getAllTowns())

        supportFragmentManager.beginTransaction()
                .add(R.id.a_main_lyt_container, townListFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()



    }

    fun goToCreation(view: View) {

        val createTownFragment = CreateTownFragment()

        supportFragmentManager.beginTransaction()
                .add(R.id.a_main_lyt_container, createTownFragment)
                .addToBackStack("createTownFragment")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
        a_main_btn_creation.visibility = View.GONE


    }

    /**Toolbar Settings**/
    /*Put this code into the right activity (to be created)*/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_town_view, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_delete -> {
                /**TO DO**/
                Toast.makeText(this, "Delete resource", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onTownCreated(town: Town) {
        townlist.addTown(town)
        this.closeCreateFragment()
    }

    override fun closeCreateFragment() {
        val bookListFragment = TownListFragment.newInstance(townlist.getAllTowns())

        supportFragmentManager.beginTransaction()
                .replace(R.id.a_main_lyt_container, bookListFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()

        a_main_btn_creation.visibility = View.VISIBLE
    }

    fun favourite(){
        /**To Do**/
    }

}