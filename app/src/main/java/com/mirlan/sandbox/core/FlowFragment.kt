package com.mirlan.sandbox.core

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mirlan.sandbox.R
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import java.lang.Exception

abstract class FlowFragment(layoutRes: Int) : BaseFragment(layoutRes) {

    val currentFragment get() = childFragmentManager.findFragmentById(R.id.container)
    abstract val navigatorHolder: NavigatorHolder
    protected open val navigator: Navigator by lazy {
        object : SupportAppNavigator(requireActivity(), childFragmentManager, R.id.container) {
            override fun activityBack() {
                onExit()
            }

            override fun setupFragmentTransaction(
                command: Command,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                fragmentTransaction.setReorderingAllowed(true)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    abstract fun onExit()

    override fun onBackPressed() {
        if (currentFragment is BaseFragment)
            (currentFragment as BaseFragment).onBackPressed()
        else throw Exception("${currentFragment!!::class.java.canonicalName} is not child of BaseFragment")
    }

}