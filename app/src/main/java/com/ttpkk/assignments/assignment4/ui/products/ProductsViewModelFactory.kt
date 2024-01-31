package com.ttpkk.assignments.assignment4.ui.products

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ttpkk.assignments.assignment4.model.Category

@Suppress("UNCHECKED_CAST")
class ProductsViewModelFactory(private val category: Category) : ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductsViewModel() as T
    }
}