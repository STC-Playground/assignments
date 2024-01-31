package com.ttpkk.assignments.assignment4.preferrence

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ttpkk.assignments.assignment4.model.ProductImage

class Shared {
    companion object {
        @JvmStatic
        @BindingAdapter("app:image")
        fun loadProductImage(imageView: ImageView, productImage: ProductImage?) {
            if (productImage != null) {
                // Use Glide or any other image loading library to load the image
                Glide.with(imageView)
                    .load(productImage.imagePath)
                    .into(imageView)
            }
        }


    }
}