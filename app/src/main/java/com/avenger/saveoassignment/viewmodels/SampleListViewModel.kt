package com.avenger.saveoassignment.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.avenger.saveoassignment.datamodels.SampleResponse
import com.avenger.saveoassignment.repository.SampleRepository
import com.avenger.saveoassignment.showmodel.ShowModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SampleListViewModel(private val sampleRepository: SampleRepository) : ViewModel() {

    fun getAllResponse(key: String): LiveData<ArrayList<SampleResponse>> {
        return liveData(Dispatchers.IO) {
            try {
                var data: ArrayList<SampleResponse>? = null
                withContext(Dispatchers.Main) {
                    data = sampleRepository.getAllResponse(key).value
                }
                if (data != null) {
                    emit(data!!)
                }
            } catch (e: Exception) {
                Log.d("TAG", "response exception: $e")
            }
        }
    }

    fun getByPage(i: Int): LiveData<ArrayList<ShowModel>> {
        return liveData(Dispatchers.IO) {
            try {
                var data: ArrayList<ShowModel>? = null
                withContext(Dispatchers.Main) {
                    data = sampleRepository.getByPage(i).value
                }
                Log.d("TAG", "response: ${data?.size}")
                if (data != null) {
                    emit(data!!)
                }
            } catch (e: Exception) {
                Log.d("TAG", "response exception: $e")
            }
        }
    }


    fun setShowModelObj(showModel: ShowModel) {
        sampleRepository.setShowModelObj(showModel)
    }

    fun getShowModelObj(): ShowModel {
        return sampleRepository.getShowModelObj()
    }

}