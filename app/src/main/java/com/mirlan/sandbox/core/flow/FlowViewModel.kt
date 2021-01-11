package com.mirlan.sandbox.core.flow

import com.mirlan.sandbox.core.BaseViewModel
import ru.terrakok.cicerone.Cicerone

open class FlowViewModel : BaseViewModel() {
    private val cicerone = Cicerone.create()
    open val navigatorHolder = cicerone.navigatorHolder
    open val childRouter = cicerone.router

    init {
        this.onStart()
    }

    open fun onStart() {}
}