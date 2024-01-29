package com.ttpkk.assignments.assignment4.preferrence

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.ttpkk.assignments.assignment4.model.ProductImage

class Shared {
    companion object {
        fun hideKeyboard(activity: Activity) {
            val view = activity.currentFocus
            val methodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            if (view != null) {
                methodManager.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
            }
        }

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