package com.mirlan.sandbox.core.flow

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import timber.log.Timber

open class FlowSupportAppNavigator(
    activity: FragmentActivity,
    private val fm: FragmentManager,
    @IdRes containerId: Int,
    val onExit: () -> Unit
) : SupportAppNavigator(activity, fm, containerId) {

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