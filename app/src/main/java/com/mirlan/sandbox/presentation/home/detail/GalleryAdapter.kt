package com.mirlan.sandbox.presentation.home.detail

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class GalleryAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    var images = emptyList<String>()
    override fun getItem(position: Int) = ImageFragment().apply {
        arguments = Bundle().apply {
            putString(ImageFragment.IMAGE_URI, images[position])
        }
    }

    override fun getCount() = images.size

}