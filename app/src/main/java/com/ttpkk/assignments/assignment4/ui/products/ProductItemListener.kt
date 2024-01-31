package com.ttpkk.assignments.assignment4.ui.products

import android.view.View
import com.ttpkk.assignments.assignment4.model.Product

interface ProductItemListener {
    fun onProductItemClick(view: View, product: Product)
}