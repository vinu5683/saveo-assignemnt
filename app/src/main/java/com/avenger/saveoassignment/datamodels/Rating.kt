package com.avenger.saveoassignment.datamodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Rating(

	@field:SerializedName("average")
	val average: Any? = null
)