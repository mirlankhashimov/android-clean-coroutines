package com.mirlan.sandbox.core.navigation

import com.mirlan.sandbox.core.main.MainFlowFragment
import com.mirlan.sandbox.core.navigation.Screens
import com.mirlan.sandbox.presentation.home.HomeFragment
import com.mirlan.sandbox.presentation.home.detail.DetailFragment
import com.mirlan.sandbox.presentation.profile.ProfileFragment
import com.mirlan.sandbox.presentation.search.SearchFragment
import ru.terrakok.cicerone.Screen

class ScreenImpl : Screens {

    override fun mainFlow(): Screen = MainFlowFragment.MainScreen

    override fun home(): Screen = HomeFragment.HomeScreen

    override fun profile(): Screen = ProfileFragment.ProfileScreen

    override fun search(): Screen = SearchFragment.SearchScreen

    override fun detail(): Screen = DetailFragment.DetailScreen
}