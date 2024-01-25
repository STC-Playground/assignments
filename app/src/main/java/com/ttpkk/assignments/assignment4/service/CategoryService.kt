package com.ttpkk.assignments.assignment4.service

import com.ttpkk.assignments.assignment4.model.Categories
import retrofit2.Response
import retrofit2.http.GET

interface CategoryService {

    @GET("1.json")
    suspend fun getCategories() : Response<Categories>
}