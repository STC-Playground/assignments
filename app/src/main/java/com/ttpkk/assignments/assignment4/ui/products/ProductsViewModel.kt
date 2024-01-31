package com.ttpkk.assignments.assignment4.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ttpkk.assignments.assignment4.model.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {
    private lateinit var job: Job
    private val _products = MutableLiveData<Category>()
    private val _headerText = MutableLiveData<String>()
    private val _isLoading = MutableLiveData<Boolean>()

    val products: LiveData<Category> get() = _products
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        _headerText.value = ""
    }

    fun getProducts(category: Category) {
        job = CoroutineScope(Dispatchers.Main).launch {
            _products.value = category
        }
    }

    fun reloadProduct(category: Category) {
        _isLoading.value = true

        viewModelScope.launch {
            delay(2000)

            getProducts(category)
            _isLoading.value = false
        }
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }

}