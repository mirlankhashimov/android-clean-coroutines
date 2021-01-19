package com.mirlan.sandbox.core.main

import com.mirlan.sandbox.core.flow.FlowViewModel
import com.mirlan.sandbox.utils.Constants.CR_MAIN_HOLDER
import com.mirlan.sandbox.utils.Constants.CR_MAIN_ROUTER
import org.koin.core.inject
import org.koin.core.qualifier.named
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import timber.log.Timber

class MainViewModel : FlowViewModel() {
    override val childRouter: Router by inject(named(CR_MAIN_ROUTER))
    override val navigatorHolder: NavigatorHolder by inject(named(CR_MAIN_HOLDER))

    fun openHome() {
        Timber.e("openHome")
        childRouter.navigateTo(screens.homeFlow())
    }

    fun openSearch() {
        childRouter.navigateTo(screens.searchFlow())
    }

    fun openProfile() {
        childRouter.navigateTo(screens.profileFlow())
    }

    fun start() {
        childRouter.newRootScreen(screens.homeFlow())
    }
}