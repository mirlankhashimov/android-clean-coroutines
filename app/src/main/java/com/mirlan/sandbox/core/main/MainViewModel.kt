package com.mirlan.sandbox.core.main

import com.mirlan.sandbox.core.BaseViewModel
import com.mirlan.sandbox.core.navigation.AMRouter
import com.mirlan.sandbox.utils.Constants.CR_MAIN_ROUTER
import org.koin.core.inject
import org.koin.core.qualifier.named

class MainViewModel : BaseViewModel() {
    override val router: AMRouter by inject(named(CR_MAIN_ROUTER))

    fun openHome() {
        router.navigateTo(screens.home())
    }

    fun openSearch() {
        router.navigateTo(screens.search())
    }

    fun openProfile() {
        router.navigateTo(screens.profile())
    }

    override fun exit() {
        router.finishFlow()
    }

    fun start() {
        router.newRootScreen(screens.home())
    }
}