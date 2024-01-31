package com.ttpkk.assignments.assignment4.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ttpkk.assignments.assignment4.model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private lateinit var job: Job
    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> get() = _product

    fun getProduct(product: Product) {
        job = CoroutineScope(Dispatchers.Main).launch {
            _product.value = product
        }
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }


}
