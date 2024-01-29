package com.ttpkk.assignments.assignment4.ui.product

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProductViewModel : ViewModel() {}
private val _text = MutableLiveData<String>().apply {
    value = ""
}
val text: LiveData<String> = _text