package com.ttpkk.assignments.assignment4.model

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("CategoryName")
    val categoryName: String,

    @SerializedName("CategoryDescription")
    val categoryDescription: String,

    @SerializedName("Products")
    val products: ArrayList<Product>
)
