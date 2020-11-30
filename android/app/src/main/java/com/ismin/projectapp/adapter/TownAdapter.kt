package com.ismin.projectapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ismin.projectapp.R
import com.ismin.projectapp.Town
import com.ismin.projectapp.TownViewHolder

class TownAdapter (private val towns: ArrayList<Town>) : RecyclerView.Adapter<TownViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TownViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_town, parent, false)
        return TownViewHolder(row)
    }

    override fun getItemCount(): Int {
        return this.towns.size
    }

    override fun onBindViewHolder(holder: TownViewHolder, position: Int) {
        val (city, population, country) = this.towns[position]

        holder.txvCity.text = city
        holder.txvPopulation.text = population.toString()
        holder.txvCountry.text = country
    }


}