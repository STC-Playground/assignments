package com.ttpkk.assignments.assignment4.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ttpkk.assignments.assignment4.model.Categories
import com.ttpkk.assignments.assignment4.preferrence.Coroutines
import com.ttpkk.assignments.assignment4.service.CategoriesRepository
import kotlinx.coroutines.Job

class CategoryViewModel(private val repository: CategoriesRepository) : ViewModel() {

    private lateinit var job: Job

    private val _categories = MutableLiveData<Categories>()
    val categories: LiveData<Categories>
        get() = _categories

    fun getCategories() {
        job = Coroutines.ioThenMain(
            { repository.getCategories() },
            { _categories.value = it})
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }


}