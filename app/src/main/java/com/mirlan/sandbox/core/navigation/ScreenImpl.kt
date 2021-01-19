package com.mirlan.sandbox.core.navigation

import com.mirlan.sandbox.core.main.MainFlowFragment
import com.mirlan.sandbox.presentation.bottom_sheet.BottomDialogFragment
import com.mirlan.sandbox.presentation.bottom_sheet.FullScreenFragment
import com.mirlan.sandbox.presentation.home.HomeFragment
import com.mirlan.sandbox.presentation.home.detail.DetailFragment
import com.mirlan.sandbox.presentation.home.flow.HomeFlowFragment
import com.mirlan.sandbox.presentation.profile.ProfileFragment
import com.mirlan.sandbox.presentation.profile.flow.ProfileFlowFragment
import com.mirlan.sandbox.presentation.search.SearchFragment
import com.mirlan.sandbox.presentation.search.flow.SearchFlowFragment
import ru.terrakok.cicerone.Screen

class ScreenImpl : Screens {

    override fun mainFlow(): Screen = MainFlowFragment.MainScreen()

    override fun home(): Screen = HomeFragment.HomeScreen()
    override fun homeFlow(): Screen = HomeFlowFragment.HomeFlowScreen

    override fun profile(): Screen = ProfileFragment.ProfileScreen()
    override fun profileFlow(): Screen = ProfileFlowFragment.ProfileFlowScreen

    override fun search(): Screen = SearchFragment.SearchScreen()
    override fun searchFlow(): Screen = SearchFlowFragment.SearchFlowScreen

    override fun detail(id: Int): Screen = DetailFragment.DetailScreen(id)

    override fun bottomDialog(): Screen = BottomDialogFragment.BottomDialogScreen
    override fun bottomFullDialog(): Screen = FullScreenFragment.FullDialogScreen
}