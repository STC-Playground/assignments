package com.ttpkk.assignments.assignment4.service

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object {
        val BASE_URL = "https://thitipat-stc.github.io/Pages/"

        fun getRetrofitInstance() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory (GsonConverterFactory.create(GsonBuilder().create()))
                .build()
        }
    }


}