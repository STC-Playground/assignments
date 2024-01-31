package com.ttpkk.assignments.assignment4.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.facebook.shimmer.ShimmerFrameLayout
import com.ttpkk.assignments.assignment4.model.Category
import com.ttpkk.assignments.assignment4.model.Product
import com.ttpkk.assignments.databinding.FragmentProductsBinding

@Suppress("DEPRECATION")
class ProductsFragment : Fragment(), ProductItemListener {

    private lateinit var viewModel: ProductsViewModel
    private lateinit var viewModelFactory: ProductsViewModelFactory
    private lateinit var loadingItem: ShimmerFrameLayout
    private lateinit var srlLayout: SwipeRefreshLayout

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
        srlLayout = binding.srlLayout

        category = args.categoryItem


        viewModelFactory = ProductsViewModelFactory(category)
        viewModel = ViewModelProvider(this,viewModelFactory).get(ProductsViewModel::class.java)

        viewModel.getProducts(category)

        viewModel.apply {
            loadingItem.visibility = View.VISIBLE
            loadingItem.duration
            loadingItem.startShimmerAnimation()
        }

        viewModel.products.observe(viewLifecycleOwner, Observer { category ->
//            Log.d("CATEGORY: ",category.toString())

            binding.tvProductsHeader.text = category.categoryName
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

        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.srlLayout.isRefreshing = it
        }

        binding.srlLayout.setOnRefreshListener {
            viewModel.reloadProduct(category)
        }
    }

    override fun onProductItemClick(view: View, product: Product) {
        val action = ProductsFragmentDirections.actionNavProductsToNavProduct(product)
        findNavController().navigate(action)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}