package com.ismin.projectapp.retrofit

import com.ismin.projectapp.Town
import retrofit2.Call
import retrofit2.http.*
import java.util.ArrayList

interface IRequests {


    @GET("towns")
    fun allTowns() : Call<ArrayList<Town>>

    @GET("favorite")
    fun allFavorites() : Call<ArrayList<Town>>

    @POST("towns")
    fun createTown(@Body() town: Town) : Call<Town>

    @POST("favorite")
    fun addToFavorite(@Body() town: Town) : Call<Town>

    @DELETE("towns/{City}")
    fun removeTown(@Path("City") City: String ) : Call<String>

    @DELETE("favorite/{City}")
    fun removeFromFavorite(@Path("City") City: String ) : Call<String>


    // Get A Particular City according to its name
    @GET("cities/{city_name}")
    fun listTowns(@Path("city_name") city_name : String?): Town
}