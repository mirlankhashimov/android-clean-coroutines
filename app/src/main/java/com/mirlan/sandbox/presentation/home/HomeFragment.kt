package com.mirlan.sandbox.presentation.home

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import com.mirlan.sandbox.data.vo.Status
import com.mirlan.sandbox.databinding.FragmentHomeBinding
import com.mirlan.sandbox.domain.entity.RecommendedFirm
import com.mirlan.sandbox.utils.*
import kotlinx.android.synthetic.main.fragment_home.progressBar
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    object HomeScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return HomeFragment()
        }
    }

    private val viewModel: HomeViewModel by sharedViewModel()
    private val albumAdapter by lazy {
        RecommendationAdapter(
            this::onSelectAlbum
        )
    }
    private val binding by viewBinding(FragmentHomeBinding::bind)

    companion object {
        const val RECOMMENDATION_ID = "recommendationId"
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.albumRv.adapter = albumAdapter
        observe(viewModel.salons) { response ->
            when (response.status) {
                Status.LOADING -> progressBar.show()
                Status.SUCCESS -> {
                    progressBar.hide()
                    albumAdapter.submitList(response.data?.data?.recommendedFirms)
                }
                else -> progressBar.hide()
            }
        }
        binding.button.setSafeOnClickListener {
            viewModel.openDetail()
        }

    }

    private fun onSelectAlbum(recommendedFirm: RecommendedFirm) {
        viewModel.openDetail()
    }

    override fun onBackPressed() {
        TODO("Not yet implemented")
    }
}