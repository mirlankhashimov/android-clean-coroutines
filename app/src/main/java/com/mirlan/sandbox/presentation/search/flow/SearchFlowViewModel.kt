package com.mirlan.sandbox.presentation.search.flow

import com.mirlan.sandbox.core.flow.FlowViewModel

class SearchFlowViewModel : FlowViewModel() {
    override fun onStart() {
        childRouter.newRootScreen(screens.search())
    }
}