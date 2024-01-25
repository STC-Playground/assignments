package com.ttpkk.assignments.assignment4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ttpkk.assignments.assignment4.model.Categories
import com.ttpkk.assignments.assignment4.model.Category
import com.ttpkk.assignments.databinding.FragmentAllProductBinding
import com.ttpkk.assignments.databinding.ItemRecyclerviewLayoutBinding

//class CategoryAdapter(private var categoriesList: List<Category>) :
//    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
//
//    inner class ViewHolder(private val binding: FragmentAllProductBinding): RecyclerView.ViewHolder(binding.root) {
////        fun bind(categories: Categories) {
////
////        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val binding = FragmentAllProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return ViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int {
//        return categoriesList.size
//    }
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val categoryItem = categoriesList[position]
////        holder.bind(categoryItem)
//    }
//
//}
