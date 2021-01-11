package com.mirlan.sandbox.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mirlan.sandbox.core.navigation.AMRouter
import com.mirlan.sandbox.core.navigation.Screens
import com.mirlan.sandbox.utils.Constants.CR_APP_ROUTER
import com.mirlan.sandbox.utils.Constants.CR_MAIN_ROUTER
import com.mirlan.sandbox.utils.PrivateLiveData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.core.KoinComponent
import org.koin.core.get
import org.koin.core.inject
import org.koin.core.qualifier.named
import ru.terrakok.cicerone.Router
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel(), KoinComponent, CoroutineScope {

    val appRouter: AMRouter by inject(named(CR_APP_ROUTER))
    val mainRouter: AMRouter by inject(named(CR_MAIN_ROUTER))
    val screens by inject<Screens>()

    open lateinit var router: Router

    override val coroutineContext = viewModelScope.coroutineContext

    open fun exit() = router.exit()

    val vmScope by lazy {
        CoroutineScope(viewModelScope.coroutineContext + get<CoroutineExceptionHandler>())
    }

    protected var <T> PrivateLiveData<T>.data
        set(value) = this.set(value)
        get() = this.value

    protected fun <T> PrivateLiveData<T>.update(transform: (T?) -> T?) {
        data = transform(data)
    }

}