package com.avenger.saveoassignment.showmodel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Image(

	@field:SerializedName("original")
	val original: String? = null,

	@field:SerializedName("medium")
	val medium: String? = null
)