package com.mirlan.sandbox.core.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.*
import java.lang.RuntimeException
import java.util.*

open class MainNavigator(
    private val activity: FragmentActivity,
    private val fragmentManager: FragmentManager,
    private val containerId: Int
) : Navigator {

    private var localStackCopy: LinkedList<String>? = null

    override fun applyCommands(commands: Array<out Command>) {
        fragmentManager.executePendingTransactions()

        //copy stack before apply commands
        copyStackToLocal()

        for (command in commands) {
            applyCommand(command)
        }
    }

    private fun copyStackToLocal() {
        localStackCopy = LinkedList()

        val stackSize = fragmentManager.backStackEntryCount
        for (i in 0 until stackSize) {
            localStackCopy?.add(fragmentManager.getBackStackEntryAt(i).name ?: "")
        }
    }

    private fun applyCommand(command: Command) {
        when (command) {
            is Forward -> fragmentForward(command)
            is Replace -> fragmentReplace(command)
            is BackTo -> backTo(command)
            is Back -> fragmentBack()
        }
    }

    private fun fragmentForward(command: Forward) {
        val screen = command.screen as SupportAppScreen

        var fragment = fragmentManager.findFragmentByTag(screen.screenKey)

        if (fragment == null) fragment = createFragment(screen)

        val fragmentTransaction = fragmentManager.beginTransaction()

        setupFragmentTransaction(
            command,
            fragmentManager.findFragmentById(containerId),
            fragment, fragmentTransaction
        )
        fragmentTransaction.replace(containerId, fragment, screen.screenKey)
            .addToBackStack(screen.screenKey).commit()
        localStackCopy?.add(screen.screenKey)
    }

    private fun fragmentBack() {
        if (localStackCopy?.size ?: 0 > 0) {
            fragmentManager.popBackStack()
            localStackCopy?.removeLast()
        } else {
            activity.finish()
        }
    }

    private fun fragmentReplace(command: Replace) {
        val screen = command.screen as SupportAppScreen
        val fragment = createFragment(screen)

        if (localStackCopy?.size ?: 0 > 0) {
            fragmentManager.popBackStack()
            localStackCopy?.removeLast()

            val fragmentTransaction = fragmentManager.beginTransaction()

            setupFragmentTransaction(
                command,
                fragmentManager.findFragmentById(containerId),
                fragment,
                fragmentTransaction
            )
            fragmentTransaction
                .replace(containerId, fragment)
                .addToBackStack(screen.screenKey)
                .commit()
            localStackCopy?.add(screen.screenKey)
        } else {
            val fragmentTransaction = fragmentManager.beginTransaction()

            setupFragmentTransaction(
                command,
                fragmentManager.findFragmentById(containerId),
                fragment,
                fragmentTransaction
            )

            fragmentTransaction.replace(containerId, fragment).commit()

        }

    }

    private fun backTo(command: BackTo) {
        if (command.screen == null) {
            backToRoot()
        } else {
            val key = command.screen!!.screenKey
            val index = localStackCopy!!.indexOf(key)
            val size = localStackCopy!!.size

            if (index != -1) {
                for (i in 1 until size - index) {
                    localStackCopy?.removeLast()
                }
                fragmentManager.popBackStack(key, 0)
            } else {
                backToRoot()
            }
        }
    }

    private fun backToRoot() {
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        localStackCopy?.clear()
    }

    private fun createFragment(screen: SupportAppScreen) =
        screen.fragment ?: throw RuntimeException("Can not create a screen:" + screen.screenKey)

    fun topScreenName() = if (localStackCopy.isNullOrEmpty()) null else localStackCopy?.last()

    protected open fun setupFragmentTransaction(
        command: Command,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction
    ) {
    }
}