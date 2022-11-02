package com.hairmunk.app.ui.map

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hairmunk.app.databinding.FragmentMapBinding
import net.daum.mf.map.api.MapView

class MapFragment : Fragment() {

    private lateinit var binding: FragmentMapBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)

        val mapView = MapView(context)

        binding.mapView.addView(mapView)

        return binding.root
    }
}