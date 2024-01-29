package com.ttpkk.assignments.assignment4.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @SerializedName("ProductName")
    val productName: String,
    @SerializedName("ProductDescription")
    val productDescription: String,
    @SerializedName("Images")
    val productImages: ArrayList<ProductImage>
) : Parcelable