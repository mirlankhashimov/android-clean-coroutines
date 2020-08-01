package com.mirlan.sandbox.presentation.home

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import com.mirlan.sandbox.data.vo.Status
import com.mirlan.sandbox.domain.entity.RecommendedFirm
import com.mirlan.sandbox.utils.hide
import com.mirlan.sandbox.utils.observe
import com.mirlan.sandbox.utils.show
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val viewModel: HomeViewModel by sharedViewModel()
    private val albumAdapter by lazy {
        RecommendationAdapter(
            this::onSelectAlbum
        )
    }

    companion object {
        const val RECOMMENDATION_ID = "recommendationId"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        album_rv.adapter = albumAdapter
        viewModel.salons.observe(viewLifecycleOwner) { response ->
            when (response.status) {
                Status.LOADING -> progressBar.show()
                Status.SUCCESS -> {
                    progressBar.hide()
                    albumAdapter.submitList(response.data?.data?.recommendedFirms)
                }
                else -> progressBar.hide()
            }
        }
    }

    private fun onSelectAlbum(recommendedFirm: RecommendedFirm) {
        val bundle = bundleOf(RECOMMENDATION_ID to recommendedFirm.id)
        navigate(R.id.action_home_screen_to_detail_screen, bundle)
    }
}