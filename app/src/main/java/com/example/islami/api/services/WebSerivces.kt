package com.example.islami.api.services

import com.example.islami.api.model.QuranResponse
import retrofit2.Call
import retrofit2.http.GET

interface WebServices {

    @GET("radios")
    fun getRadio():Call<QuranResponse>
}
