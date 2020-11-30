package com.ismin.projectapp.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ismin.projectapp.R
import com.ismin.projectapp.Town
import com.ismin.projectapp.activity.TownListActivity
import com.ismin.projectapp.adapter.TownAdapter
import kotlinx.android.synthetic.main.fragment_town_list.*

private const val ARG_TOWNS = "ARG_TOWNS"

class TownListFragment(townsList: ArrayList<Town>) : Fragment() {

    private var towns: ArrayList<Town> = townsList
    private lateinit var rcvTowns: RecyclerView


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
        this.rcvTowns.adapter = TownAdapter(towns)
        val linearLayoutManager = LinearLayoutManager(context)
        this.rcvTowns.layoutManager = linearLayoutManager

        val dividerItemDecoration = DividerItemDecoration(context, linearLayoutManager.orientation)
        this.rcvTowns.addItemDecoration(dividerItemDecoration)

        return rootView;
    }

    fun coucou(view: View) {

        Log.v("456","here!!!")
        val t_activity: TownListActivity = activity as TownListActivity
        //t_activity.test(view)
/**
        val createTownFragment =
                CreateTownFragment()

        fragmentManager?.beginTransaction()
                ?.add(R.id.a_main_lyt_container, createTownFragment)
                ?.addToBackStack("createTownFragment")
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                ?.commit()
        a_main_btn_creation.visibility = View.GONE**/

    }
    companion object {
        @JvmStatic
        fun newInstance(towns: ArrayList<Town>) =
                TownListFragment(towns).apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_TOWNS, ArrayList(towns))
                    }
                }
    }
}
