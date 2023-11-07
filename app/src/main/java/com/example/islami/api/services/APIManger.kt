package com.example.islami.api.services

import android.util.Log
import com.example.islami.constant
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIManger {

    companion object{

        var retrofit: Retrofit? =null
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(object :HttpLoggingInterceptor.Logger {
                override fun log(message: String) {
                    Log.e("API",message)
                }
            })

        val okHttpClient = OkHttpClient
            .Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        fun getInstance():Retrofit{
            if(retrofit == null){
                retrofit = Retrofit
                    .Builder()
                    .baseUrl(constant.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit!!
        }

        fun getWebService():WebServices{
            return getInstance().create(WebServices::class.java)
        }
    }
}