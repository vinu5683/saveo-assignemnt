package com.avenger.saveoassignment.datamodels

import com.google.gson.annotations.SerializedName

data class SampleResponse(

    @field:SerializedName("score")
    val score: Double? = null,

    @field:SerializedName("show")
    val show: Show? = null
)