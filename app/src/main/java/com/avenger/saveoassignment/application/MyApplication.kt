package com.avenger.saveoassignment.application

import android.app.Application
import com.avenger.saveoassignment.repository.SampleRepository

class MyApplication() : Application() {

    val repository by lazy {
        SampleRepository()
    }
}