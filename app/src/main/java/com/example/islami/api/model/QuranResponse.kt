package com.example.islami.api.model

import com.google.gson.annotations.SerializedName

data class QuranResponse(
	@field:SerializedName("radios")
	val radios: List<RadiosItem>? = null
)
