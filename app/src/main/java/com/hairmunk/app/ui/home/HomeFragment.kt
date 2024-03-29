package com.hairmunk.app.ui.home

import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
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
import com.hairmunk.app.utils.BaseFragment

class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), ProductClickListener {

    private val viewModel: HomeViewModel by viewModels { ViewModelFactory(requireContext()) }

    private lateinit var bannerAdapter: HomeBannerAutoAdapter
    var imgList = ArrayList<HomeAd>()

    override fun init() {

        // 상단바 투명도 조정
        val outLocation = IntArray(2)

        binding.homeMainToolbar.getLocationOnScreen(outLocation)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.homeScrollview.setOnScrollChangeListener { p0, p1, p2, p3, p4 ->
                val statusBarAlpha = (p2 * 0.425).toInt()
                val menuAlpha = (255 - (p2 * 0.85).toInt())
                val menuTint = if (p2 > 300) R.color.black_01 else R.color.white
                if (p2 > 600) {
                    requireActivity().window.statusBarColor = Color.argb(255, 255, 255, 255)
                } else {
                    requireActivity().window.statusBarColor = Color.argb(statusBarAlpha, 255, 255, 255)
                }

                binding.homeMainToolbar.background.alpha = statusBarAlpha
                if (p2 > 300) {
                    binding.ivHomeMenu.background.alpha = ((p2 - 300) * 0.85).toInt()
                    binding.ivHomeAlarm.background.alpha = ((p2 - 300) * 0.85).toInt()
                    binding.ivHomeSearch.background.alpha = ((p2 - 300) * 0.85).toInt()
                    binding.ivHomeMenu.background.setTint(ContextCompat.getColor(requireActivity(), menuTint))
                    binding.ivHomeAlarm.background.setTint(ContextCompat.getColor(requireActivity(), menuTint))
                    binding.ivHomeSearch.background.setTint(ContextCompat.getColor(requireActivity(), menuTint))
                } else if (p2 < 300){
                    binding.ivHomeMenu.background.alpha = menuAlpha
                    binding.ivHomeAlarm.background.alpha = menuAlpha
                    binding.ivHomeSearch.background.alpha = menuAlpha
                    binding.ivHomeMenu.background.setTint(ContextCompat.getColor(requireActivity(), R.color.white))
                    binding.ivHomeAlarm.background.setTint(ContextCompat.getColor(requireActivity(), R.color.white))
                    binding.ivHomeSearch.background.setTint(ContextCompat.getColor(requireActivity(), R.color.white))
                }
            }
        }

        // 무한 viewpager
        imgList.add(HomeAd("https://user-images.githubusercontent.com/96619472/203496017-041e8c9b-3cb6-43dd-880f-2e07d87e720f.png"))
        imgList.add(HomeAd("https://user-images.githubusercontent.com/96619472/203496020-1c0093f3-47a3-450f-b8b9-2058586fe531.png"))
        imgList.add(HomeAd("https://user-images.githubusercontent.com/96619472/203496021-22d04b29-924f-4998-83a1-35fdfd272a39.png"))
        imgList.add(HomeAd("https://user-images.githubusercontent.com/96619472/203496022-397c4593-0231-4fb4-8d7d-d4ca5a9c43ab.png"))
        imgList.add(HomeAd("https://user-images.githubusercontent.com/96619472/203496025-b370a232-a7b8-4b3a-af7e-86965db2d88c.png"))
        imgList.add(HomeAd("https://user-images.githubusercontent.com/96619472/203496026-5732ebd5-6627-4b8d-a146-84ae7ea4379a.png"))
        imgList.add(HomeAd("https://user-images.githubusercontent.com/96619472/203496028-f80f04a4-5759-40d0-adf2-a1fa326ba714.png"))
        imgList.add(HomeAd("https://user-images.githubusercontent.com/96619472/203496030-f77a5ae2-084d-497b-a0df-3303d9c80435.png"))
        imgList.add(HomeAd("https://user-images.githubusercontent.com/96619472/203496033-c0385de8-fb97-413b-9814-f0633dead061.png"))

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


        // 상태 바 투명으로 설정
        requireActivity().setStatusBarTransparent()

        binding.homeMainToolbar.setPadding(
            0,
            requireContext().statusBarHeight(),
            0,
            0
        )

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