package com.ismin.projectapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class TownViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    var txvCity = itemView.findViewById<TextView>(R.id.r_town_txv_city)
    var txvPopulation = itemView.findViewById<TextView>(R.id.r_town_txv_population)
    var txvCountry = itemView.findViewById<TextView>(R.id.r_town_txv_country)

}