package com.hairmunk.app.ui.categorydetail

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.hairmunk.app.utils.BaseFragment
import com.hairmunk.app.R
import com.hairmunk.app.common.KEY_CATEGORY_LABEL
import com.hairmunk.app.databinding.FragmentCategoryDetailBinding
import com.hairmunk.app.ui.common.ProductClickListener
import com.hairmunk.app.ui.common.ProductPromotionAdapter
import com.hairmunk.app.ui.common.SectionTitleAdapter
import com.hairmunk.app.ui.common.ViewModelFactory

class CategoryDetailFragment : BaseFragment<FragmentCategoryDetailBinding>(R.layout.fragment_category_detail), ProductClickListener {

    private val viewModel: CategoryDetailViewModel by viewModels { ViewModelFactory(requireContext()) }

    override fun init() {
        setToolbar()
        setListAdapter()    }

    override fun onProductClick(productId: String) {
    }

    private fun setToolbar() {
        val categoryLabel = requireArguments().getString(KEY_CATEGORY_LABEL)
        binding.toolbarCategoryDetail.title = categoryLabel
    }

    private fun setListAdapter() {
        val topSellingSectionAdapter = CategoryTopSellingSectionAdapter()
        val titleAdapter = SectionTitleAdapter()
        val promotionAdapter = ProductPromotionAdapter(this)
        binding.rvCategoryDetail.adapter = ConcatAdapter(topSellingSectionAdapter, titleAdapter, promotionAdapter)
        viewModel.topSelling.observe(viewLifecycleOwner) { topSelling->
            topSellingSectionAdapter.submitList(listOf(topSelling))
        }
        viewModel.promotion.observe(viewLifecycleOwner) { promotions ->
            titleAdapter.submitList(listOf(promotions.title))
            promotionAdapter.submitList(promotions.items)
        }
    }
}