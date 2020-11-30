package com.ismin.projectapp

import java.io.Serializable

class TownList : Serializable {

    private val storage = HashMap<String, Town>()

    fun addTown(town: Town) {
        this.storage[town.city] = town
    }

    fun getTown(city: String): Town? {
        return this.storage[city]
    }

    fun getAllTowns(): ArrayList<Town> {
        return ArrayList(this.storage.values)
    }

    fun getTownsofCountry(country: String): List<Town> {
        val filteredStorage = this.storage.filter { it.value.country == country }
        return ArrayList(filteredStorage.values).sortedBy { town -> town.city }
    }

    fun getTotalNumberOfTowns(): Int {
        return this.storage.size
    }
}