package com.ttpkk.assignments.assignment4.service

class CategoriesRepository(
    private val api: CategoriesApi
) : SafeApiRequest() {
    suspend fun getCategories() = apiRequest { api.getCategories() }
}