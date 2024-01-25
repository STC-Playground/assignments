package com.ttpkk.assignments.assignment4.products

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.ttpkk.assignments.R
import com.ttpkk.assignments.assignment4.model.Categories
import com.ttpkk.assignments.assignment4.service.CategoryService
import com.ttpkk.assignments.assignment4.service.RetrofitInstance
import retrofit2.Response

class AllProductFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val retrofitService = RetrofitInstance
            .getRetrofitInstance()
            .create(CategoryService::class.java)

        val responseLiveData : LiveData<Response<Categories>> = liveData {
            val response = retrofitService.getCategories()
            emit(response)
        }

        responseLiveData.observe(viewLifecycleOwner, Observer {
            val categoriesList = it.body()?.categories?.listIterator()

            if (categoriesList != null) {
                while (categoriesList.hasNext()) {
                    val categories = categoriesList.next()
                    Log.i("Categories", categories.toString())
                }
            }
        })



        return inflater.inflate(R.layout.fragment_all_product, container, false)
    }

}