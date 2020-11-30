package com.ismin.projectapp.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.cardview.widget.CardView
import com.ismin.projectapp.R
import com.ismin.projectapp.Town

class CreateTownFragment : Fragment() {

    private var activity: TownCreator? = null
    private lateinit var edtCity: EditText
    private lateinit var edtPopulation: EditText
    private lateinit var edtCountry: EditText
    private lateinit var card: CardView
    private lateinit var rootLayout: ViewGroup
    private lateinit var blackView: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_create_town, container, false)

        rootLayout = rootView.findViewById(R.id.create_town_root_lyt)
        edtCity = rootView.findViewById(R.id.f_create_town_edt_name)
        edtPopulation = rootView.findViewById(R.id.f_create_town_edt_pop)
        edtCountry = rootView.findViewById(R.id.f_create_town_edt_country)
        card = rootView.findViewById(R.id.f_create_town_card)
        blackView = rootView.findViewById(R.id.f_create_town_black_view)

        rootView.setOnClickListener { activity?.closeCreateFragment() }
        rootView.findViewById<Button>(R.id.f_create_town_btn_save).setOnClickListener {
            saveTown()
        }

        return rootView
    }

    fun saveTown() {
        activity?.onTownCreated(
            Town(
                edtCity.text.toString(),
                edtPopulation.text.toString().toInt(),
                edtCountry.text.toString()
            )
        )
    }

    override fun onAttach(context: Context) {
        if (context is TownCreator) {
            activity = context
        }
        super.onAttach(context)
    }
}


interface TownCreator {
    fun onTownCreated(town: Town)
    fun closeCreateFragment()
}