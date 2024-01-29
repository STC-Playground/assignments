package com.ttpkk.assignments.assignment4.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.ttpkk.assignments.assignment4.model.Category
import com.ttpkk.assignments.assignment4.model.Product
import com.ttpkk.assignments.databinding.FragmentProductsBinding

@Suppress("DEPRECATION")
class ProductsFragment : Fragment(), ProductItemListener {

    private lateinit var viewModel: ProductsViewModel
    private lateinit var viewModelFactory: ProductsViewModelFactory
    private lateinit var loadingItem: ShimmerFrameLayout

    private val args : ProductsFragmentArgs by navArgs()
    private lateinit var category: Category

    private var _binding: FragmentProductsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductsBinding.inflate(inflater, container, false)
        val root: View = binding.root
       return  root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadingItem = binding.shimmerView

        category = args.categoryItem


        viewModelFactory = ProductsViewModelFactory(category)
        viewModel = ViewModelProvider(this,viewModelFactory).get(ProductsViewModel::class.java)

        viewModel.getProducts(category)

        viewModel.apply {
            loadingItem.visibility = View.VISIBLE
            loadingItem.startShimmerAnimation()
        }

        viewModel.headerText.observe(viewLifecycleOwner, Observer {
            binding.tvProductsHeader.text = category.categoryName
        })

        viewModel.products.observe(viewLifecycleOwner, Observer { category ->
//            Log.d("CATEGORY: ",category.toString())
            if (category != null) {
                loadingItem.stopShimmerAnimation()
                loadingItem.visibility = View.GONE
            }

            binding.rvCategory.also { it ->
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter =  ProductsAdapter(category, this)
            }

        })
    }

    override fun onProductItemClick(view: View, categoryName: String, product: Product) {
        Toast.makeText(view.context,"YESSSSSS", Toast.LENGTH_SHORT).show()
    }


}