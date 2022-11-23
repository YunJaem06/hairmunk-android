package com.hairmunk.app.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.asksira.loopingviewpager.LoopingPagerAdapter
import com.bumptech.glide.Glide
import com.hairmunk.app.R
import com.hairmunk.app.model.HomeAd

class HomeBannerAutoAdapter(itemList: ArrayList<HomeAd>, isInfinite: Boolean, context: Context) : LoopingPagerAdapter<HomeAd>(itemList, isInfinite) {
    val mContext = context
    var position : Int = 0

    override fun bindView(convertView: View, listPosition: Int, viewType: Int) {
        var img = convertView.findViewById<ImageView>(R.id.iv_home_ad_banner)
        Glide.with(mContext)
            .load(itemList!![listPosition].ImageUrl)
            .centerCrop()
            .into(img)
        position = listPosition
    }

    override fun inflateView(viewType: Int, container: ViewGroup, listPosition: Int): View {
        return LayoutInflater.from(container.context).inflate(R.layout.item_home_ad_banner, container, false)
    }
}