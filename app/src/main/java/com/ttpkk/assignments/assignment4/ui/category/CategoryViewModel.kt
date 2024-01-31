package com.ttpkk.assignments.assignment4.ui.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ttpkk.assignments.assignment4.model.Categories
import com.ttpkk.assignments.assignment4.preferrence.Coroutines
import com.ttpkk.assignments.assignment4.service.CategoriesRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class CategoryViewModel(private val repository: CategoriesRepository) : ViewModel() {

    private lateinit var job: Job
    private val _categories = MutableLiveData<Categories>()
    private val _isLoading = MutableLiveData<Boolean>()
    val categories: LiveData<Categories> get() = _categories
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun getCategories() {
        job = Coroutines.ioThenMain(
            { repository.getCategories() },
            { _categories.value = it})
    }

    fun reloadCategories() {
        _isLoading.value = true

        viewModelScope.launch {
            delay(2000)

            getCategories()
            _isLoading.value = false
        }
    }


    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }


}