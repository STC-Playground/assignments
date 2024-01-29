package com.ttpkk.assignments.assignment4.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductImage(
    @SerializedName("ImagePath")
    var imagePath : String
) : Parcelable
