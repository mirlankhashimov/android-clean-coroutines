package com.mirlan.sandbox.core.navigation

import com.mirlan.sandbox.core.DialogInterface
import ru.terrakok.cicerone.Screen

interface Screens {

    fun mainFlow(): Screen

    fun home(): Screen
    fun homeFlow(): Screen

    fun profile(): Screen
    fun profileFlow(): Screen

    fun search(): Screen
    fun searchFlow(): Screen

    fun detail(id: Int): Screen

    fun bottomDialog(): Screen
    fun bottomFullDialog(): Screen
    fun dialog(
        title: String,
        confirmTitle: String,
        cancelTitle: String
    ): Screen
}