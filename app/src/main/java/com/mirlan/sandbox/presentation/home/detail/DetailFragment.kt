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
import com.mirlan.sandbox.databinding.FragmentDetailBinding
import com.mirlan.sandbox.domain.entity.Salon
import com.mirlan.sandbox.presentation.home.HomeFragment.Companion.RECOMMENDATION_ID
import com.mirlan.sandbox.presentation.home.HomeViewModel
import com.mirlan.sandbox.utils.hide
import com.mirlan.sandbox.utils.link
import com.mirlan.sandbox.utils.show
import com.mirlan.sandbox.utils.viewBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailFragment : BaseFragment(R.layout.fragment_detail) {

    private val viewModel: HomeViewModel by sharedViewModel()
    private val galleryAdapter by lazy { GalleryAdapter(childFragmentManager) }
    private val masterAdapter by lazy { MasterAdapter() }
    private val serviceAdapter by lazy { ServiceAdapter() }
    private val binding by viewBinding(FragmentDetailBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt(RECOMMENDATION_ID)?.let { id -> viewModel.loadSalon(id) }
        setupCollapsingToolbar()
        setupGallery()
        observeSalon()
        setClicks()
    }

    private fun setClicks() {
        binding.salonToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeSalon() {
        viewModel.salon.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> binding.progressBar.show()
                Status.SUCCESS -> {
                    binding.progressBar.hide()
                    setData(it.data?.data)
                }
                else -> binding.progressBar.hide()
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
        binding.salonCollapsingToolbar.title = salon?.firm?.name
        galleryAdapter.notifyDataSetChanged()
        salon?.firm.let { firm ->
            binding.salonName.text = firm?.name
            binding.salonType.text = firm?.type
            binding.salonAddress.text = firm?.address
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.salonAddress.text =
                    Html.fromHtml(firm?.address, Html.FROM_HTML_MODE_COMPACT);
            } else {
                binding.salonAddress.text = Html.fromHtml(firm?.address);
            }
            binding.salonRating.text = firm?.checkRating.toString()
            binding.salonReviewCount.text = "${firm?.reviewCount} voted"
            binding.salonWorkTime.text = firm?.workStartTime + " - " + firm?.workEndTime
            binding.salonDetailWorkPhone1.show(firm?.phoneNumbers?.firstOrNull())
            firm?.instagramProfile?.let { binding.salonDetailInstagram.link("instagram", it) }
        }
        salon?.services?.let { services ->
            if (services.isNotEmpty()) {
                binding.salonDetailServiceListLayout.show()
                binding.salonDetailServiceList.adapter = serviceAdapter
                serviceAdapter.submitList(services)
            }
        }
        salon?.masters?.let { masterList ->
            if (masterList.isNotEmpty()) {
                binding.salonDetailMasterListLayout.show()
                binding.salonDetailMasterList.adapter = masterAdapter
                masterAdapter.submitList(masterList)
            }
        }
    }

    private fun setupGallery() {
        binding.salonGallery.adapter = galleryAdapter
        binding.beautySalonGalleryIndicator.setupWithViewPager(binding.salonGallery)
    }

    private fun setupCollapsingToolbar() {
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(binding.salonToolbar)
            supportActionBar?.apply {
                setDisplayHomeAsUpEnabled(true)
            }
        }
        var previousIsBlack = true
        binding.salonAppbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            val collapsingToolbar = binding.salonCollapsingToolbar
            val minimumCollapseHeight = 2 * ViewCompat.getMinimumHeight(collapsingToolbar)
            val openedCollapseHeight = collapsingToolbar.height + verticalOffset
            val isBlack = openedCollapseHeight < minimumCollapseHeight
            if (previousIsBlack == isBlack) return@OnOffsetChangedListener
            val color = if (isBlack) Color.BLACK else Color.WHITE
            binding.salonToolbar.navigationIcon?.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)
            val titleColor = if (isBlack) Color.BLACK else Color.TRANSPARENT
            binding.salonToolbar.title = getString(R.string.detail)
            binding.salonToolbar.setTitleTextColor(titleColor)
            previousIsBlack = isBlack
        })
    }
}