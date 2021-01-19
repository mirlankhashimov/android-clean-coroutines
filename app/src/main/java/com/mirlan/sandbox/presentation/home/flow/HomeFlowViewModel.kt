package com.mirlan.sandbox.presentation.home.flow

import com.mirlan.sandbox.core.flow.FlowViewModel

class HomeFlowViewModel : FlowViewModel() {
    override fun onStart() {
        childRouter.newRootScreen(screens.home())
    }
}