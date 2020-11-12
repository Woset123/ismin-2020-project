package com.ismin.projectapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_TOWNS = "ARG_TOWNS"

class TownListFragment : Fragment() {
    private lateinit var towns: ArrayList<Town>
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

    companion object {
        @JvmStatic
        fun newInstance(books: List<Town>) =
                TownListFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_TOWNS, ArrayList(books))
                    }
                }
    }
}
