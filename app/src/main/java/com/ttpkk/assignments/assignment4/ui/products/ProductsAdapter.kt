package com.ttpkk.assignments.assignment4.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ttpkk.assignments.R
import com.ttpkk.assignments.assignment4.model.Category
import com.ttpkk.assignments.assignment4.model.Product
import com.ttpkk.assignments.databinding.ItemProductLayoutBinding

class ProductsAdapter(
    private val category: Category,
    private val listener: ProductItemListener ) :
    RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ProductViewHolder (
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_product_layout,
                parent,
                false

        )
    )

    override fun getItemCount() = category.products.size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(category.products[position])
        holder.itemProductLayoutBinding.root.setOnClickListener {
            listener.onProductItemClick(holder.itemProductLayoutBinding.root, category.products[position])
        }

    }
    inner class ProductViewHolder(
        val itemProductLayoutBinding: ItemProductLayoutBinding
    ) : RecyclerView.ViewHolder(itemProductLayoutBinding.root) {

        fun bind(product: Product) {
            itemProductLayoutBinding.productImage = product.productImages.get(0)
            itemProductLayoutBinding.product = product
            itemProductLayoutBinding.executePendingBindings()
        }
    }

}