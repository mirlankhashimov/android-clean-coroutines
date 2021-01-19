package com.mirlan.sandbox.core.main

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.mirlan.sandbox.core.flow.FlowSupportAppNavigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.*

class MainNavigator(
    activity: FragmentActivity,
    fragmentManager: FragmentManager,
    containerId: Int,
    onExit: () -> Unit
) : FlowSupportAppNavigator(activity, fragmentManager, containerId, onExit) {

    fun topScreenName() = localStackCopy.lastOrNull()

    override fun fragmentForward(command: Forward) {
        val screen = command.screen as SupportAppScreen

        val fragmentParams = screen.fragmentParams
        val oldFragment = fragmentManager.findFragmentByTag(screen.screenKey)
        var fragment = oldFragment ?: if (fragmentParams == null) createFragment(screen) else null

        val fragmentTransaction = fragmentManager.beginTransaction()

        setupFragmentTransaction(
            command,
            fragmentManager.findFragmentById(containerId),
            fragment,
            fragmentTransaction
        )
        if (fragmentParams != null) {
            fragmentTransaction.replace(
                containerId,
                fragmentParams.fragmentClass,
                fragmentParams.arguments,
                screen.screenKey
            )
        } else {
            fragmentTransaction.replace(containerId, fragment!!, screen.screenKey)
        }
        fragmentTransaction.addToBackStack(screen.screenKey).commit()
        localStackCopy?.add(screen.screenKey)
    }

    override fun fragmentReplace(command: Replace) {
        val screen = command.screen as SupportAppScreen
        val fragmentParams = screen.fragmentParams
        val oldFragment = fragmentManager.findFragmentByTag(screen.screenKey)
        val fragment = oldFragment ?: if (fragmentParams == null) createFragment(screen) else null

        if (localStackCopy.size > 0) {
            fragmentManager.popBackStack()
            localStackCopy.removeLast()
            val fragmentTransaction = fragmentManager.beginTransaction()
            setupFragmentTransaction(
                command,
                fragmentManager.findFragmentById(containerId),
                fragment,
                fragmentTransaction
            )
            if (fragmentParams != null) {
                fragmentTransaction.replace(
                    containerId,
                    fragmentParams.fragmentClass,
                    fragmentParams.arguments,
                    screen.screenKey
                )
            } else {
                fragmentTransaction.replace(containerId, fragment!!, screen.screenKey)
            }
            fragmentTransaction.addToBackStack(screen.screenKey).commit()
            localStackCopy.add(screen.screenKey)
        } else {
            val fragmentTransaction = fragmentManager.beginTransaction()

            setupFragmentTransaction(
                command,
                fragmentManager.findFragmentById(containerId),
                fragment,
                fragmentTransaction
            )

            fragmentTransaction.replace(containerId, fragment!!, screen.screenKey).commit()

        }

    }
}