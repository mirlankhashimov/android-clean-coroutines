package com.mirlan.sandbox.core.navigation

import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import timber.log.Timber

class AMRouter(val router: AMRouter?) : Router() {
    private val listeners = mutableMapOf<Int, (Any) -> Unit>()

    fun startFlow(screen: Screen) {
        if (router == null) {
            Timber.e("router is null")
        } else {
            router.navigateTo(screen)
        }
    }

    fun newRootFlow(screen: Screen) {
        if (router == null) {
            Timber.e("router is null")
        } else {
            router.newRootFlow(screen)
        }
    }

    fun finishFlow() {
        if (router == null) {
            Timber.e("router is null")
        } else {
            router.exit()
        }
    }

    fun setLResultListener(code: Int, callback: (Any) -> Unit) {
        listeners[code] = callback
    }

    fun removeResultListener(code: Int) {
        listeners.remove(code)
    }

    fun exitWithResult(code: Int, data: Any, count: Int = 1) {
        repeat(count) { exit() }
        listeners[code]?.invoke(data)
    }

    fun closeScreen(count: Int = 1) {
        repeat(count) { exit() }
    }
}