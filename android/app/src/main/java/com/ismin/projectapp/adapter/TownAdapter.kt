package com.ismin.projectapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ismin.projectapp.R
import com.ismin.projectapp.Town
import com.ismin.projectapp.fragment.TownListFragment

class TownAdapter(private val towns: ArrayList<Town>, private val listener: OnItemClickListener) : RecyclerView.Adapter<TownAdapter.TownViewHolder>() {



    inner class TownViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener, View.OnLongClickListener {

        val itemCity: TextView = itemView.findViewById(R.id.r_town_txv_city)
        val itemCountry: TextView = itemView.findViewById(R.id.r_town_txv_country)
        val itemPopulation: TextView = itemView.findViewById(R.id.r_town_txv_population)


        init {
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onItemClick(itemCity.text.toString(), itemCountry.text.toString(), itemPopulation.text.toString())
        }

        override fun onLongClick(v: View?): Boolean {
            listener.onItemLongClick(itemCity.text.toString(), itemCountry.text.toString(), itemPopulation.text.toString())
            return true
        }

    }

    interface OnItemClickListener {
        fun onItemClick(City: String, Country: String, Population: String)
        fun onItemLongClick(City: String, Country: String, Population: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TownViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_town, parent, false)
        return TownViewHolder(row)
    }

    override fun getItemCount(): Int {
        return this.towns.size
    }

    override fun onBindViewHolder(holder: TownViewHolder, position: Int) {
        val (city, country, population) = this.towns[position]

        holder.itemCity.text = city
        holder.itemPopulation.text = population
        holder.itemCountry.text = country
    }

}


