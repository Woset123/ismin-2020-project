package com.ismin.projectapp.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.ismin.projectapp.R
import com.ismin.projectapp.Town
import com.ismin.projectapp.TownList
import com.ismin.projectapp.activity.CreateTownActivity
import com.ismin.projectapp.activity.FocusTownActivity
import com.ismin.projectapp.activity.TownListActivity
import com.ismin.projectapp.adapter.TownAdapter

private const val ARG_TOWNS = "ARG_TOWNS"

class TownListFragment(townsList: ArrayList<Town>, favList: TownList) : Fragment(), TownAdapter.OnItemClickListener {

    private var towns: ArrayList<Town> = townsList
    private var favTowns: ArrayList<Town> = favList.getAllTowns()
    private lateinit var rcvTowns: RecyclerView

    private val FocusTownActivityRequestCode = 1;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            towns = it.getSerializable(ARG_TOWNS) as ArrayList<Town>
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_town_list, container, false)

        this.rcvTowns = rootView.findViewById(R.id.f_town_list_rcv_towns)
        this.rcvTowns.adapter = TownAdapter(towns, this)
        val linearLayoutManager = LinearLayoutManager(context)
        this.rcvTowns.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        this.rcvTowns.addItemDecoration(dividerItemDecoration)


        // Listener when Click on button to Add a new town
        val button = rootView.findViewById<FloatingActionButton>(R.id.a_main_btn_creation)
        button.setOnClickListener { view -> (activity as TownListActivity).createNewTown(view)}

        return rootView;
    }



    companion object {
        @JvmStatic
        fun newInstance(towns: ArrayList<Town>, favTowns: TownList) =
                TownListFragment(towns, favTowns ).apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_TOWNS, ArrayList(towns))
                    }
                }
    }

    override fun onItemClick(City: String, Country: String, Population: String) {

        val intent = Intent(activity, FocusTownActivity::class.java)
            .putExtra("city", City)
            .putExtra("country", Country)
            .putExtra("population", Population)
        startActivityForResult(intent, this.FocusTownActivityRequestCode)
    }
}
