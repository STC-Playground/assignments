package com.ttpkk.assignments.assignment4.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    @SerializedName("CategoryName")
    val categoryName: String,

    @SerializedName("CategoryDescription")
    val categoryDescription: String,

    @SerializedName("Products")
    val products: ArrayList<Product>
) : Parcelable
