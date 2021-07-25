package com.avenger.saveoassignment.showmodel

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Network(

	@field:SerializedName("country")
	val country: Country? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null
)