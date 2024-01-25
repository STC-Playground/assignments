package com.ttpkk.assignments.assignment4.model

import com.google.gson.annotations.SerializedName

data class ProductImage(
    @SerializedName("ImagePath")
    val imagePath : String
)
