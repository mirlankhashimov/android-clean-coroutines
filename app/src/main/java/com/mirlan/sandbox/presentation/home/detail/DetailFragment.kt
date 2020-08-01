package com.mirlan.sandbox.presentation.home.detail

import GalleryAdapter
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.AppBarLayout
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import com.mirlan.sandbox.data.vo.Status
import com.mirlan.sandbox.domain.entity.Salon
import com.mirlan.sandbox.presentation.home.HomeFragment.Companion.RECOMMENDATION_ID
import com.mirlan.sandbox.presentation.home.HomeViewModel
import com.mirlan.sandbox.utils.hide
import com.mirlan.sandbox.utils.link
import com.mirlan.sandbox.utils.show
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailFragment : BaseFragment(R.layout.fragment_detail) {
    private val viewModel: HomeViewModel by sharedViewModel()
    private val galleryAdapter by lazy { GalleryAdapter(childFragmentManager) }
    private val masterAdapter by lazy { MasterAdapter() }
    private val serviceAdapter by lazy { ServiceAdapter() }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(RECOMMENDATION_ID)?.let { id -> viewModel.loadSalon(id) }
        setupCollapsingToolbar()
        setupGallery()
        observeSalon()
        setClicks()
    }

    private fun setClicks() {
        salon_toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeSalon() {
        viewModel.salon.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> progressBar.show()
                Status.SUCCESS -> {
                    progressBar.hide()
                    setData(it.data?.data)
                }
                else -> progressBar.hide()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setData(salon: Salon?) {
        salon?.firm?.pictures.let {
            if (it != null) {
                galleryAdapter.images = it
            }
        }
        galleryAdapter.notifyDataSetChanged()
        salon?.firm.let { firm ->
            salon_name.text = firm?.name
            salon_type.text = firm?.type
            salon_address.text = firm?.address
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                salon_address.text =
                    Html.fromHtml(firm?.address, Html.FROM_HTML_MODE_COMPACT);
            } else {
                salon_address.text = Html.fromHtml(firm?.address);
            }
            salon_rating.text = firm?.checkRating.toString()
            salon_review_count.text = "${firm?.reviewCount} voted"
            salon_work_time.text = firm?.workStartTime + " - " + firm?.workEndTime
            salon_detail_work_phone_1.show(firm?.phoneNumbers?.firstOrNull())
            firm?.instagramProfile?.let { salon_detail_instagram.link("instagram", it) }
        }
        salon?.services?.let { services ->
            if (services.isNotEmpty()) {
                salon_detail_service_list_layout.show()
                salon_detail_service_list.adapter = serviceAdapter
                serviceAdapter.submitList(services)
            }
        }
        salon?.masters?.let { masterList ->
            if (masterList.isNotEmpty()) {
                salon_detail_master_list_layout.show()
                salon_detail_master_list.adapter = masterAdapter
                masterAdapter.submitList(masterList)
            }
        }
    }

    private fun setupGallery() {
        salon_gallery.adapter = galleryAdapter
        beauty_salon_gallery_indicator.setupWithViewPager(salon_gallery)
    }

    private fun setupCollapsingToolbar() {
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(salon_toolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
            }
        }
        var previousIsBlack = true
        salon_appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val collapsingToolbar = salon_collapsing_toolbar
            val minimumCollapseHeight = 2 * ViewCompat.getMinimumHeight(collapsingToolbar)
            val openedCollapseHeight = collapsingToolbar.height + verticalOffset
            val isBlack = openedCollapseHeight < minimumCollapseHeight
            if (previousIsBlack == isBlack) return@OnOffsetChangedListener
            val color = if (isBlack) Color.BLACK else Color.WHITE
            salon_toolbar.navigationIcon?.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            val titleColor = if (isBlack) Color.BLACK else Color.TRANSPARENT
            salon_toolbar.title = getString(R.string.detail)
            salon_toolbar.setTitleTextColor(titleColor)
            previousIsBlack = isBlack
        })
    }
}