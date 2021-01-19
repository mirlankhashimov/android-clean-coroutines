package com.mirlan.sandbox.presentation.profile.flow

import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.flow.FlowFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.terrakok.cicerone.android.support.SupportAppScreen

class ProfileFlowFragment : FlowFragment(R.layout.flow_fragment) {
    object ProfileFlowScreen : SupportAppScreen() {
        override fun getFragment() = ProfileFlowFragment()
    }

    override val viewModel: ProfileFlowViewModel by viewModel()
}