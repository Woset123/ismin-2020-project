package com.ismin.projectapp.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Pojo(@field:Expose @field:SerializedName("Country") var country: String, @field:Expose @field:SerializedName("City") var city: String, @field:Expose @field:SerializedName("AccentCity") var accentCity: String, @field:Expose @field:SerializedName("Region") var region: Int, @field:Expose @field:SerializedName("Population") var population: Int, @field:Expose @field:SerializedName("Latitude") var latitude: Double, @field:Expose @field:SerializedName("Longitude") var longitude: Double, @field:Expose @field:SerializedName("geopoint") var geopoint: String) 