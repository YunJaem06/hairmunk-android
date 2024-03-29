package com.hairmunk.app.ui.category

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.hairmunk.app.utils.BaseFragment
import com.hairmunk.app.R
import com.hairmunk.app.common.KEY_CATEGORY_ID
import com.hairmunk.app.common.KEY_CATEGORY_LABEL
import com.hairmunk.app.databinding.FragmentCategoryBinding
import com.hairmunk.app.ui.common.EventObserver
import com.hairmunk.app.ui.common.ViewModelFactory

class CategoryFragment: BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category) {

    private val viewModel: CategoryViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun init() {
        val categoryAdapter = CategoryAdapter(viewModel)
        binding.rvCategoryList.adapter = categoryAdapter
        viewModel.items.observe(viewLifecycleOwner) {
            categoryAdapter.submitList(it)
        }

        viewModel.openCategoryEvent.observe(viewLifecycleOwner, EventObserver {
            openCategoryDetail(it.categoryId, it.label)
        })    }

    private fun openCategoryDetail(categoryId: String, categoryLabel: String) {
        findNavController().navigate(R.id.action_category_to_category_detail, bundleOf(
            KEY_CATEGORY_ID to categoryId,
            KEY_CATEGORY_LABEL to categoryLabel
        ))
    }
}