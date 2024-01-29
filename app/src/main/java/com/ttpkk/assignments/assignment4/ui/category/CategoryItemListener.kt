package com.ttpkk.assignments.assignment4.ui.category

import android.view.View
import com.ttpkk.assignments.assignment4.model.Category

interface CategoryItemListener {
    fun onCategoryItemClick(view: View, category: Category)

}