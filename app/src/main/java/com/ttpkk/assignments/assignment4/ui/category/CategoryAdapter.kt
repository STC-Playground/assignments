package com.ttpkk.assignments.assignment4.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ttpkk.assignments.R
import com.ttpkk.assignments.assignment4.model.Category
import com.ttpkk.assignments.assignment4.model.ProductImage
import com.ttpkk.assignments.databinding.ItemCategoryLayoutBinding

class CategoryAdapter(
    private val categories: ArrayList<Category>,
    private val listener: CategoryItemListener) :
    RecyclerView.Adapter<CategoryAdapter.CategoriesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CategoriesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_category_layout,
                parent,
                false
            )
    )

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
//        holder.itemRecyclerviewLayoutBinding.category = lst.categories[position]
        holder.bind(categories[position],categories[position].products.get(0).productImages.get(0))
        holder.itemCategoryLayoutBinding.root.setOnClickListener {
            listener.onCategoryItemClick(holder.itemCategoryLayoutBinding.root, categories[position])
        }
    }

    override fun getItemCount() = categories.size
    inner class CategoriesViewHolder(
        val itemCategoryLayoutBinding: ItemCategoryLayoutBinding
    ) : RecyclerView.ViewHolder(itemCategoryLayoutBinding.root) {
        fun bind(category: Category,productImage: ProductImage) {
            // Bind data to your data binding layout
            itemCategoryLayoutBinding.category = category
            itemCategoryLayoutBinding.productImage = productImage
            // Execute bindings immediately to refresh UI
            itemCategoryLayoutBinding.executePendingBindings()
        }
    }

}
