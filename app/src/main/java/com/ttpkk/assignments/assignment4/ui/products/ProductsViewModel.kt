package com.ttpkk.assignments.assignment4.ui.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ttpkk.assignments.assignment4.model.Category
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {
    private lateinit var job: Job
    private val _products = MutableLiveData<Category>()
    private val _headerText = MutableLiveData<String>()

    val products: LiveData<Category>
        get() = _products


    val headerText: LiveData<String>
        get() = _headerText

    init {
        _headerText.value = ""
    }

    fun getProducts(category: Category) {
        job = CoroutineScope(Dispatchers.Main).launch {
            _products.value = category
            _headerText.value = category.categoryName

        }
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }

}