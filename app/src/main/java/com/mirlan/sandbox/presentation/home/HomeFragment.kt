package com.mirlan.sandbox.presentation.home

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import com.mirlan.sandbox.data.vo.Resource
import com.mirlan.sandbox.data.vo.Status
import com.mirlan.sandbox.databinding.FragmentHomeBinding
import com.mirlan.sandbox.domain.entity.RecommendedFirm
import com.mirlan.sandbox.domain.entity.Salon
import com.mirlan.sandbox.utils.*
import kotlinx.android.synthetic.main.fragment_home.progressBar
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import ru.terrakok.cicerone.android.support.SupportAppScreen
import timber.log.Timber

class HomeFragment : BaseFragment(R.layout.fragment_home) {

    class HomeScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return HomeFragment()
        }
    }

    override val viewModel: HomeViewModel by sharedViewModel()
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
            Timber.e("adfasdf")
            showDialog()
            //viewModel.openDetail()
        }
        launch(viewModel.uiState) {
            handleState(it)
        }

    }

    private fun handleState(resource: Resource<List<Salon>>) {
        when (resource.status) {
            Status.SUCCESS -> {
            }
        }
    }

    override fun onResume() {
        super.onResume()
        Timber.e("resumeHome")
    }

    private fun onSelectAlbum(recommendedFirm: RecommendedFirm) {
        viewModel.openDetail()
    }
}