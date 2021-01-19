package com.mirlan.sandbox.presentation.home.flow

import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.flow.FlowFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.terrakok.cicerone.android.support.SupportAppScreen
import timber.log.Timber

class HomeFlowFragment : FlowFragment(R.layout.flow_fragment) {
    object HomeFlowScreen : SupportAppScreen() {
        override fun getFragment() = HomeFlowFragment()
    }

    override fun onResume() {
        super.onResume()
        Timber.e("onResume")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.e("onDestroy")
    }

    override fun onStart() {
        super.onStart()
        Timber.e("start")
    }

    override val viewModel: HomeFlowViewModel by viewModel()
}