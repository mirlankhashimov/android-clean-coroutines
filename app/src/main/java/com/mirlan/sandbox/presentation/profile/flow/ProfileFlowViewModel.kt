package com.mirlan.sandbox.presentation.profile.flow

import com.mirlan.sandbox.core.flow.FlowViewModel

class ProfileFlowViewModel : FlowViewModel() {
    override fun onStart() {
        childRouter.newRootScreen(screens.profile())
    }
}