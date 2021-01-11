package com.mirlan.sandbox

import com.mirlan.sandbox.core.BaseViewModel

class AppViewModel : BaseViewModel() {

    fun start() {
        appRouter.replaceScreen(screens.mainFlow())
    }
}