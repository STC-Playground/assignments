package com.ttpkk.assignments.assignment4.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.facebook.shimmer.ShimmerFrameLayout
import com.ttpkk.assignments.assignment4.model.Product
import com.ttpkk.assignments.assignment4.ui.products.ProductsFragmentArgs
import com.ttpkk.assignments.databinding.FragmentProductBinding

class ProductFragment : Fragment() {

    private lateinit var viewModel: ProductViewModel
    private lateinit var viewModelFactory: ProductViewModelFactory
    private lateinit var loadingItem: ShimmerFrameLayout

    private val args : ProductsFragmentArgs by navArgs()
    private lateinit var product: Product

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}