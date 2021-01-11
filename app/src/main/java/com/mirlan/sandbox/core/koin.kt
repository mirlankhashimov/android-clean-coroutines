package com.mirlan.sandbox.core

import com.mirlan.sandbox.AppViewModel
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
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.terrakok.cicerone.Cicerone

val mainModule = module {
    single(named(CR_APP)) { Cicerone.create(AMRouter(null)) }
    single(named(CR_APP_ROUTER)) { get<Cicerone<AMRouter>>(named(CR_APP)).router }
    single(named(CR_APP_HOLDER)) { get<Cicerone<AMRouter>>(named(CR_APP)).navigatorHolder }

    single { ScreenImpl() as Screens }

    single(named(CR_MAIN)) { Cicerone.create(AMRouter(get(named(CR_APP_ROUTER)))) }
    single(named(CR_MAIN_ROUTER)) { get<Cicerone<AMRouter>>(named(CR_MAIN)).router }
    single(named(CR_MAIN_HOLDER)) { get<Cicerone<AMRouter>>(named(CR_MAIN)).navigatorHolder }

    viewModel(override = true) { MainViewModel() }
    viewModel(override = true) { AppViewModel() }
}