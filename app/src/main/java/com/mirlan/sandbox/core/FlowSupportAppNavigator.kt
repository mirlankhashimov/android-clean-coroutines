package com.mirlan.sandbox.core

import android.app.Activity
import android.os.Handler
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

open class FlowSupportAppNavigator(
    activity: FragmentActivity,
    fm: FragmentManager,
    @IdRes containerId: Int,
    val onExit: () -> Unit
) : SupportAppNavigator(activity, fm, containerId) {
    override fun applyCommands(commands: Array<out Command>) {
        Handler().post { super.applyCommands(commands) }
    }

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