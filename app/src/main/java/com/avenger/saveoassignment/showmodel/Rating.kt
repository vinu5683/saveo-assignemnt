package com.avenger.saveoassignment.showmodel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating(
    @field:SerializedName("average")
    val average: Double? = null
)