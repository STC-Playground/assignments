package com.ttpkk.assignments.assignment4.ui.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ttpkk.assignments.assignment4.service.CategoriesRepository

@Suppress("UNCHECKED_CAST")
class CategoryViewModelFactory(private val repository: CategoriesRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModel(repository) as T
    }
}