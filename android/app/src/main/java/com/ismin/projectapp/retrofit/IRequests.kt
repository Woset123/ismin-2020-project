package com.ismin.projectapp.retrofit

import com.ismin.projectapp.Town
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import java.util.ArrayList

interface IRequests {



    @GET("towns")
    fun allTowns() : Call<ArrayList<Town>>

    @POST("towns")
    fun createTown(@Body() town: Town) : Call<Town>


    // Get A Particular City according to its name
    @GET("cities/{city_name}")
    fun listTowns(@Path("city_name") city_name : String?): Town
}