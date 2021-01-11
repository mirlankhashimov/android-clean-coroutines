package com.mirlan.sandbox.core.navigation

import ru.terrakok.cicerone.Screen

interface Screens {
    fun mainFlow(): Screen
    fun home(): Screen
    fun profile(): Screen
    fun search(): Screen
    fun detail(): Screen
}