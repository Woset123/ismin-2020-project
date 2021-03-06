package com.ismin.projectapp

import android.os.Build
import androidx.annotation.RequiresApi
import retrofit2.Call
import java.io.Serializable

class TownList : Serializable {

    private val storage = HashMap<String, Town>()

    fun addTown(town: Town) {
        this.storage[town.City] = town
    }

    fun getTown(City: String): Town? {
        return this.storage[City]
    }

    fun getAllTowns(): ArrayList<Town> {
        return ArrayList(this.storage.values.sortedBy { town -> town.Country })
    }

    fun getTownsofCountry(Country: String): ArrayList<Town> {
        val filteredStorage = this.storage.filter { it.value.Country == Country }
        return ArrayList(filteredStorage.values.sortedBy { town -> town.Country })
    }

    fun getTotalNumberOfTowns(): Int {
        return this.storage.size
    }

    fun exist(town: Town, list: ArrayList<Town>): Boolean {
        return list.contains(town)
    }

    fun search(City: String): ArrayList<Town> {
        val filter = this.storage.filterValues { it.City == City }
        return ArrayList(filter.values)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun removeTown(city: String) {
        for ((key, value) in storage) {
            if (value.City==city) {
                storage.remove(key,value)
            }
        }
    }
}