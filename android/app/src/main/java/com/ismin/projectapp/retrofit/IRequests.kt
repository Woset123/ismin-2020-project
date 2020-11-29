package com.ismin.projectapp.retrofit

import com.ismin.projectapp.Town
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IRequests {

    //
    @get:GET("cities")
    val city: Call<List<Pojo?>?>?

    // Get A Particular City according to its name
    @GET("cities/{city_name}")
    fun listTowns(@Path("city_name") city_name : String?): Call<Town?>

}