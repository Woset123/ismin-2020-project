package com.ismin.projectapp

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
        return ArrayList(this.storage.values)
    }

    fun getTownsofCountry(Country: String): List<Town> {
        val filteredStorage = this.storage.filter { it.value.Country == Country }
        return ArrayList(filteredStorage.values).sortedBy { town -> town.City }
    }

    fun getTotalNumberOfTowns(): Int {
        return this.storage.size
    }
}