package com.ttpkk.assignments.assignment4.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("ProductName")
    val productName: String,
    @SerializedName("ProductDescription")
    val productDescription: String,
    @SerializedName("Images")
    val productImages: ArrayList<ProductImage>
)
