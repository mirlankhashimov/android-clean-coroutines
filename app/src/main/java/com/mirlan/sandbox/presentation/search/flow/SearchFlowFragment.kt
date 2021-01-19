package com.mirlan.sandbox.presentation.search.flow

import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.flow.FlowFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

class SearchFlowFragment : FlowFragment(R.layout.flow_fragment) {
    object SearchFlowScreen : SupportAppScreen() {
        override fun getFragment() = SearchFlowFragment()
    }

    override val viewModel: SearchFlowViewModel by viewModel()
}