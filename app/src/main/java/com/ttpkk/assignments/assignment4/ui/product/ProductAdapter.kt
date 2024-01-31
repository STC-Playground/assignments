package com.ttpkk.assignments.assignment4.ui.product

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.smarteist.autoimageslider.SliderViewAdapter
import com.ttpkk.assignments.R
import com.ttpkk.assignments.assignment4.model.ProductImage

class ProductAdapter(
    private val productImages: ArrayList<ProductImage>,
) : SliderViewAdapter<ProductAdapter.ProductViewHolder>() {
    override fun getCount(): Int = productImages.size

    override fun onCreateViewHolder(parent: ViewGroup?): ProductAdapter.ProductViewHolder {
        val inflate = LayoutInflater.from(parent!!.context).inflate(R.layout.content_product_image,null)
        return ProductViewHolder(inflate)
    }

    override fun onBindViewHolder(viewHolder: ProductAdapter.ProductViewHolder?, position: Int) {
        if (viewHolder!=null) {
            Glide.with(viewHolder.itemView).load(productImages[position].imagePath).fitCenter()
                .into(viewHolder.ivProduct)
        }
    }

    inner class ProductViewHolder(itemView: View?) : SliderViewAdapter.ViewHolder(itemView) {
        val ivProduct: ImageView = itemView!!.findViewById(R.id.iv_product)

    }

}
