package com.ttpkk.assignments.assignment4.ui.product

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.facebook.shimmer.ShimmerFrameLayout
import com.smarteist.autoimageslider.SliderView
import com.ttpkk.assignments.assignment4.model.Product
import com.ttpkk.assignments.databinding.FragmentProductBinding

@Suppress("DEPRECATION")
class ProductFragment : Fragment() {

    private lateinit var viewModel: ProductViewModel
    private lateinit var viewModelFactory: ProductViewModelFactory
    private lateinit var loadingItem: ShimmerFrameLayout
    private lateinit var adapter : ProductAdapter

    private val args : ProductFragmentArgs by navArgs()
    private lateinit var product: Product

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadingItem = binding.shimmerView

        product = args.productItem

        viewModelFactory = ProductViewModelFactory(product)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ProductViewModel::class.java)

        viewModel.getProduct(product)

        viewModel.apply {
            loadingItem.visibility = View.VISIBLE
            loadingItem.startShimmerAnimation()
        }
        Log.d("Product: ", product.productImages.toString())

        viewModel.product.observe(viewLifecycleOwner, Observer {
            if (product != null) {
                loadingItem.stopShimmerAnimation()
                loadingItem.visibility = View.GONE
            }
            adapter = ProductAdapter(product.productImages)
            binding.ivProductSlider.setSliderAdapter(adapter)

            binding.ivProductSlider.also {
                it.isAutoCycle = true
                it.startAutoCycle()
                it.scrollTimeInSec = 4
                it.autoCycleDirection = SliderView.LAYOUT_DIRECTION_LTR
            }

            binding.tvProductHeader.text = product.productName
            binding.tvProductDescription.text = product.productDescription

        })

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}