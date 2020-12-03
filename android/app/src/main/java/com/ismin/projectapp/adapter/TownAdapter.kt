package com.ismin.projectapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.recyclerview.widget.RecyclerView
import com.ismin.projectapp.R
import com.ismin.projectapp.Town
import com.ismin.projectapp.TownViewHolder
import com.ismin.projectapp.activity.CreateTownActivity
import com.ismin.projectapp.activity.MainActivity
import com.ismin.projectapp.activity.TownListActivity
import org.intellij.lang.annotations.JdkConstants

class TownAdapter (private val towns: ArrayList<Town>, private val listener: OnItemClickListener) : RecyclerView.Adapter<TownAdapter.TownViewHolder>() {



    inner class TownViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val itemCity: TextView = itemView.findViewById(R.id.r_town_txv_city)
        val itemCountry: TextView = itemView.findViewById(R.id.r_town_txv_country)
        val itemPopulation: TextView = itemView.findViewById(R.id.r_town_txv_population)


        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onItemClick(itemCountry.text.toString(), itemCity.text.toString(), itemPopulation.text.toString())
        }

    }

    interface OnItemClickListener {
        fun onItemClick(County: String, City: String, Population: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TownViewHolder {
        val row = LayoutInflater.from(parent.context).inflate(R.layout.row_town, parent, false)
        return TownViewHolder(row)
    }

    override fun getItemCount(): Int {
        return this.towns.size
    }

    override fun onBindViewHolder(holder: TownViewHolder, position: Int) {
        val (country, city, population) = this.towns[position]

        holder.itemCity.text = city
        holder.itemPopulation.text = population
        holder.itemCountry.text = country
    }

}


