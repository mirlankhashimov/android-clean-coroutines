package com.mirlan.sandbox.core

import android.content.Intent
import com.mirlan.sandbox.AppViewModel
import com.mirlan.sandbox.SingleActivity
import com.mirlan.sandbox.core.main.MainViewModel
import com.mirlan.sandbox.core.navigation.AMRouter
import com.mirlan.sandbox.core.navigation.ScreenImpl
import com.mirlan.sandbox.core.navigation.Screens
import com.mirlan.sandbox.utils.Constants.CR_APP
import com.mirlan.sandbox.utils.Constants.CR_APP_HOLDER
import com.mirlan.sandbox.utils.Constants.CR_APP_ROUTER
import com.mirlan.sandbox.utils.Constants.CR_MAIN
import com.mirlan.sandbox.utils.Constants.CR_MAIN_HOLDER
import com.mirlan.sandbox.utils.Constants.CR_MAIN_ROUTER
import com.mirlan.sandbox.utils.Constants.INTENT_APP_ACTIVITY
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router

val mainModule = module {
    single(named(CR_APP)) { Cicerone.create(AMRouter(null)) }
    single(named(CR_APP_ROUTER)) { get<Cicerone<AMRouter>>(named(CR_APP)).router }
    single(named(CR_APP_HOLDER)) { get<Cicerone<AMRouter>>(named(CR_APP)).navigatorHolder }

    single { ScreenImpl() as Screens }

    single(named(CR_MAIN)) { Cicerone.create(Router()) }
    single(named(CR_MAIN_ROUTER)) { get<Cicerone<Router>>(named(CR_MAIN)).router }
    single(named(CR_MAIN_HOLDER)) { get<Cicerone<Router>>(named(CR_MAIN)).navigatorHolder }

    viewModel(override = true) { MainViewModel() }
    viewModel(override = true) { AppViewModel() }

    single(named(INTENT_APP_ACTIVITY)) {
        Intent(get(), SingleActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
    }
}