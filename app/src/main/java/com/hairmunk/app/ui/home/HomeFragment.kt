package com.hairmunk.app.ui.home

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayoutMediator
import com.hairmunk.app.*
import com.hairmunk.app.common.KEY_PRODUCT_ID
import com.hairmunk.app.databinding.FragmentHomeBinding
import com.hairmunk.app.model.HomeAd
import com.hairmunk.app.ui.common.*

class HomeFragment: Fragment(), ProductClickListener {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }
    private lateinit var binding: FragmentHomeBinding

    private lateinit var bannerAdapter: HomeBannerAutoAdapter
    var imgList = ArrayList<HomeAd>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val outLocation = IntArray(2)
        val tabLocation = IntArray(2)
        binding.homeMainToolbar.getLocationOnScreen(outLocation)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.homeScrollview.setOnScrollChangeListener { p0, p1, p2, p3, p4 ->
                if (p2 > 600) {
                    requireActivity().window.statusBarColor = Color.argb(255, 255, 255, 255)
                } else {
                    requireActivity().window.statusBarColor = Color.argb((p2 * 0.425).toInt(), 255, 255, 255)
                }

                binding.homeMainToolbar.background.alpha = ((p2 * 0.425).toInt())
                if (p2 > 300) {
                    binding.ivHomeMenu.background.alpha = ((p2 - 300) * 0.85).toInt()
                    binding.ivHomeAlarm.background.alpha = ((p2 - 300) * 0.85).toInt()
                    binding.ivHomeSearch.background.alpha = ((p2 - 300) * 0.85).toInt()
                    binding.ivHomeMenu.background.setTint(ContextCompat.getColor(requireActivity(), R.color.black_01))
                    binding.ivHomeAlarm.background.setTint(ContextCompat.getColor(requireActivity(), R.color.black_01))
                    binding.ivHomeSearch.background.setTint(ContextCompat.getColor(requireActivity(), R.color.black_01))
                } else if (p2 < 300){
                    binding.ivHomeMenu.background.alpha = (255 - (p2 * 0.85).toInt())
                    binding.ivHomeAlarm.background.alpha = (255 - (p2 * 0.85).toInt())
                    binding.ivHomeSearch.background.alpha = (255 - (p2 * 0.85).toInt())
                    binding.ivHomeMenu.background.setTint(ContextCompat.getColor(requireActivity(), R.color.white))
                    binding.ivHomeAlarm.background.setTint(ContextCompat.getColor(requireActivity(), R.color.white))
                    binding.ivHomeSearch.background.setTint(ContextCompat.getColor(requireActivity(), R.color.white))
                }
            }
        }

        imgList.add(HomeAd("https://user-images.githubusercontent.com/96619472/192743737-89c73d54-655b-461c-92b6-dad9c37f5ab6.png"))
        imgList.add(HomeAd("https://user-images.githubusercontent.com/96619472/192750860-a82eeec6-d099-47f6-b837-c55e624af254.png"))

        bannerAdapter = HomeBannerAutoAdapter(imgList, true, requireActivity())
        binding.mainHomeAdVp.adapter = bannerAdapter
        binding.mainHomeAdVp.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int, ) {
            }
            override fun onPageSelected(position: Int) {
                binding.mainBannerCounter.text = "$position / ${bannerAdapter.lastItemPosition}"
            }

            override fun onPageScrollStateChanged(state: Int) {
            }

        })

        binding.lifecycleOwner = viewLifecycleOwner
        setToolbar()
        setNavigation()
        setTopBanners()
        setListAdapter()
        setHomeToolbar()
    }

    override fun onProductClick(productId: String) {
        findNavController().navigate(R.id.action_home_to_product_detail, bundleOf(
            KEY_PRODUCT_ID to "INSIGHT-1"
        ))
    }

    private fun setHomeToolbar() {
        activity?.window?.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        binding.homeMainToolbar.bringToFront()
        binding.homeMainToolbar.background.alpha = 0
    }

    private fun setToolbar() {
        viewModel.title.observe(viewLifecycleOwner) { title ->
            binding.title = title
        }
    }

    private fun setNavigation() {
        viewModel.openProductEvent.observe(viewLifecycleOwner, EventObserver { productId->
            findNavController().navigate(R.id.action_home_to_product_detail, bundleOf(
                KEY_PRODUCT_ID to productId
            ))
        })
    }

    private fun setTopBanners() {
        with(binding.viewpagerHomeBanner) {
            adapter = HomeBannerAdapter(viewModel).apply {
                viewModel.topBanners.observe(viewLifecycleOwner) { banners ->
                    submitList(banners)
                }
            }
            val pageWidth = resources.getDimension(R.dimen.viewpager_item_width)
            val pageMargin = resources.getDimension(R.dimen.viewpager_item_margin)
            val screenWidth = resources.displayMetrics.widthPixels
            val offset = screenWidth - pageWidth - pageMargin

            offscreenPageLimit = 3
            setPageTransformer { page, position ->
                page.translationX = position * -offset
            }
            TabLayoutMediator(binding.viewpagerHomeBannerIndicator, this) { tab, position ->

            }.attach()
        }
    }
    private fun setListAdapter() {
        val titleAdapter = SectionTitleAdapter()
        val promotionAdapter = ProductPromotionAdapter(this)
        binding.rvHome.adapter = ConcatAdapter(titleAdapter, promotionAdapter)
        viewModel.promotions.observe(viewLifecycleOwner){ promotions->
            titleAdapter.submitList(listOf(promotions.title))
            promotionAdapter.submitList(promotions.items)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.mainHomeAdVp.resumeAutoScroll()
    }

    override fun onPause() {
        super.onPause()
        binding.mainHomeAdVp.pauseAutoScroll()
    }
}