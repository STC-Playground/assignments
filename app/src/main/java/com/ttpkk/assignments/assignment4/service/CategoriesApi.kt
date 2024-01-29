package com.ttpkk.assignments.assignment4.service

import com.google.gson.GsonBuilder
import com.ttpkk.assignments.assignment4.model.Categories
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface CategoriesApi {
    @GET("1.json")
    suspend fun getCategories() : Response<Categories>
    companion object {
        val BASE_URL = "https://thitipat-stc.github.io/Pages/"
        operator fun invoke() : CategoriesApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory (GsonConverterFactory.create(GsonBuilder().create()))
                .build()
                .create(CategoriesApi::class.java)
        }
    }
}