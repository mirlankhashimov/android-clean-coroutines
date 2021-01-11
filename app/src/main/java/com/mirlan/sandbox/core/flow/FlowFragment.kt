package com.mirlan.sandbox.core.flow

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mirlan.sandbox.R
import com.mirlan.sandbox.core.BaseFragment
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import java.lang.Exception

abstract class FlowFragment(layoutRes: Int) : BaseFragment(layoutRes) {

    val currentFragment get() = childFragmentManager.findFragmentById(R.id.container)

    abstract override val viewModel: FlowViewModel

    protected open val navigator: Navigator by lazy {
        FlowSupportAppNavigator(
            requireActivity(),
            childFragmentManager,
            R.id.container,
            this::onExit
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            viewModel.childRouter.exit()
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