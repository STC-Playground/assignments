package com.ttpkk.assignments.assignment4.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ttpkk.assignments.assignment4.model.Product

@Suppress("UNCHECKED_CAST")
class ProductViewModelFactory(private val product: Product) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductViewModel() as T
    }
}