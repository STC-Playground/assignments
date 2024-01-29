package com.ttpkk.assignments.assignment4.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import com.ttpkk.assignments.assignment4.model.Category
import com.ttpkk.assignments.assignment4.service.CategoriesApi
import com.ttpkk.assignments.assignment4.service.CategoriesRepository
import com.ttpkk.assignments.databinding.FragmentCategoryBinding

@Suppress("DEPRECATION")
class CategoryFragment : Fragment(), CategoryItemListener {

    private lateinit var loadingItem: ShimmerFrameLayout
    private lateinit var viewModel: CategoryViewModel
    private lateinit var viewModelFactory: CategoryViewModelFactory
    private lateinit var action: NavDirections

    private var _binding: FragmentCategoryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCategoryBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return  root
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        loadingItem = binding.shimmerView

        val api = CategoriesApi()
        val repository = CategoriesRepository(api)

        viewModelFactory = CategoryViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CategoryViewModel::class.java)

        viewModel.getCategories()

        viewModel.getCategories().apply {
            loadingItem.visibility = View.VISIBLE
            loadingItem.startShimmerAnimation()
        }

        viewModel.categories.observe(viewLifecycleOwner, Observer { categories ->
//            Log.d("TAG CATEGORY", categories.categories.toString())

            if (categories != null) {
                loadingItem.stopShimmerAnimation()
                loadingItem.visibility = View.GONE
            }

            binding.rvCategory.also { it ->
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter =  CategoryAdapter(categories.categories,this)
            }

        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCategoryItemClick(view: View, category: Category) {
        val action = CategoryFragmentDirections.actionNavCategoryToNavProducts(category)
        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}