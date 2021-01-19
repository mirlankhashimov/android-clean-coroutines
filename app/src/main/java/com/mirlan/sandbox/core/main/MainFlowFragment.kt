package com.mirlan.sandbox.core.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.flow.FlowFragment
import com.mirlan.sandbox.databinding.MainFlowFragmentBinding
import com.mirlan.sandbox.presentation.home.HomeFragment
import com.mirlan.sandbox.presentation.home.flow.HomeFlowFragment
import com.mirlan.sandbox.presentation.profile.ProfileFragment
import com.mirlan.sandbox.presentation.profile.flow.ProfileFlowFragment
import com.mirlan.sandbox.presentation.search.SearchFragment
import com.mirlan.sandbox.presentation.search.flow.SearchFlowFragment
import com.mirlan.sandbox.utils.Constants.CR_MAIN_HOLDER
import com.mirlan.sandbox.utils.viewBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.qualifier.named
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppScreen
import timber.log.Timber

class MainFlowFragment : FlowFragment(R.layout.main_flow_fragment) {
    class MainScreen : SupportAppScreen() {
        override fun getFragment(): Fragment? {
            return MainFlowFragment()
        }
    }

    private val screenKeys = listOf(
        HomeFlowFragment.HomeFlowScreen,
        SearchFlowFragment.SearchFlowScreen,
        ProfileFlowFragment.ProfileFlowScreen,
    ).map { it.screenKey }

    private val binding by viewBinding(MainFlowFragmentBinding::bind)
    override val viewModel: MainViewModel by viewModel()

    override val navigator: MainNavigator by lazy {
        MainNavigator(requireActivity(), childFragmentManager, R.id.container, ::onExit)
    }

    override fun onExit() = viewModel.exit()

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> viewModel.openHome()
                R.id.navigation_search -> viewModel.openSearch()
                R.id.navigation_profile -> viewModel.openProfile()
            }
            true
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.start()
    }

    override fun onResume() {
        super.onResume()
        Timber.e("resume")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        binding.navigation.setOnNavigationItemReselectedListener {
            Timber.e("navigation")
            (currentFragment as? FlowFragment)?.viewModel?.onStart()
        }
        childFragmentManager.addOnBackStackChangedListener {
            val topScreen = navigator.topScreenName()
            val selectedIndex = screenKeys.indexOf(topScreen)
            val checkedIndex = if (selectedIndex > -1) selectedIndex else 0
            Timber.e("childe")
            binding.navigation.menu.getItem(checkedIndex).isChecked = true
        }
    }
}