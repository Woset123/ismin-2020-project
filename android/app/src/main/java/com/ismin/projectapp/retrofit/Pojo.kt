package com.ismin.projectapp.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Pojo(

        @SerializedName("City")
        var city: String,
        @SerializedName("Population")
        var population: Int,
        @SerializedName("Country")
        var country: String

) : Serializable