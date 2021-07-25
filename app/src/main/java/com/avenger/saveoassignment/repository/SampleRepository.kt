package com.avenger.saveoassignment.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.avenger.saveoassignment.api_connection.ApiService
import com.avenger.saveoassignment.api_connection.RemoteNetwork
import com.avenger.saveoassignment.datamodels.SampleResponse
import com.avenger.saveoassignment.showmodel.ShowModel

class SampleRepository {

    var completeApiResponse = MutableLiveData<ArrayList<SampleResponse>>()
    var getByPageResponse = MutableLiveData<ArrayList<ShowModel>>()

    var showModel: ShowModel

    init {
        showModel = ShowModel()
    }

    suspend fun getAllResponse(key: String): MutableLiveData<ArrayList<SampleResponse>> {
        val apiClient = RemoteNetwork.getInstance().create(ApiService::class.java)
        val res = apiClient.hitApi(key)
        completeApiResponse.value = res
        return completeApiResponse
    }

    suspend fun getByPage(i: Int): MutableLiveData<ArrayList<ShowModel>> {
        val apiClient = RemoteNetwork.getInstance().create(ApiService::class.java)
        val res = apiClient.getByPage(i)
        Log.d("TAG", "getByPage: $res")
        getByPageResponse.value = res
        return getByPageResponse
    }

    fun setShowModelObj(showModel: ShowModel) {
        this.showModel = showModel
    }

    fun getShowModelObj(): ShowModel {
        return showModel
    }


}