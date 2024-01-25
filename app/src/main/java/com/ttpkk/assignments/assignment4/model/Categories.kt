package com.ttpkk.assignments.assignment4.model

import com.google.gson.annotations.SerializedName

data class Categories(
    @SerializedName("Categories")
    val categories: ArrayList<Category>
)
