package com.ismin.projectapp.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentTransaction
import com.ismin.projectapp.R
import com.ismin.projectapp.Town
import com.ismin.projectapp.TownList
import com.ismin.projectapp.adapter.MyPagerAdapter
import com.ismin.projectapp.fragment.CreateTownFragment
import com.ismin.projectapp.fragment.TownCreator
import com.ismin.projectapp.fragment.TownListFragment
import kotlinx.android.synthetic.main.activity_town_list.*
import kotlinx.android.synthetic.main.fragment_town_list.*

class TownListActivity : AppCompatActivity(),
    TownCreator {

    private val TAG = TownListActivity::class.simpleName

    private val townlist = TownList()

    private val city_test = Town(
        city = "Paris",
        population = 2148000,
        country = "France"
    )

    private val city_test2 = Town(
            city = "Tours",
            population = 120000,
            country = "France"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_town_list)

        toolbar.setTitle("Population Cities")
        setSupportActionBar(toolbar)



        //Call
        /*var retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build()

        var service = retrofit.create(IRequests::class.java)
        var repos = service.listTowns("paris") as Town


        this.townlist.addTown(repos)*/

        this.townlist.addTown(city_test)
        this.townlist.addTown(city_test2)

        val townListFragment = TownListFragment.newInstance(townlist.getAllTowns())

        supportFragmentManager.beginTransaction()
                .add(R.id.a_main_lyt_container, townListFragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()


        val fragmentAdapter = MyPagerAdapter(supportFragmentManager, townlist.getAllTowns())
        viewpager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewpager)


    }

    fun coucou(view: View) {

        Log.v("123","gotocreation")
        val createTownFragment =
            CreateTownFragment()

        supportFragmentManager.beginTransaction()
                .add(R.id.a_main_lyt_container, createTownFragment)
                .addToBackStack("createTownFragment")
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit()
        Log.v("124","gotocreation2")
        a_main_btn_creation.visibility = View.GONE
        Log.v("125","gotocreation3")


    }

    /**Toolbar Settings**/
    /***Put this code into the right activity (to be created)
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
    }**/

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

        //a_main_btn_creation.visibility = View.VISIBLE
    }

    fun favourite(){
        /**To Do**/
    }

}