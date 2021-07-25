package com.avenger.saveoassignment.datamodels

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Links(

	@field:SerializedName("self")
	val self: Self? = null,

	@field:SerializedName("previousepisode")
	val previousepisode: Previousepisode? = null
)