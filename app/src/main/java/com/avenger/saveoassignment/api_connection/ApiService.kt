package com.avenger.saveoassignment.api_connection

import com.avenger.saveoassignment.datamodels.SampleResponse
import com.avenger.saveoassignment.showmodel.ShowModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/shows/")
    suspend fun hitApi(@Query("q") query: String): ArrayList<SampleResponse>

    @GET("shows")
    suspend fun getByPage(@Query("page") i: Int): ArrayList<ShowModel>?
}