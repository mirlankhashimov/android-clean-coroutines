package com.mirlan.sandbox.utils

import java.lang.reflect.Array.set
import java.util.*

class ResultLive<T>(private val resultFlow: ResultFlow<T>) : PrivateLiveData<Result<T>>(),
    ResultController {
    private val resultLive = resultFlow.asLiveData()
    private val observer = Observer<Result<T>>(::set)
    override fun refresh() = resultFlow.refresh()
    override fun showLoading: Boolean
    get() = resultFlow.showLoading set(value)= resultFlow::showLoading.set(value)
    override fun onActive() {
        super.onActive()
        resultLive.observeForever(observer)

    }

    override fun onInactive() {
        super.onInactive()
        resultLive.removeObserve(observer)
    }
}

fun <T> ResultFlow<T>.toResultLive(showLoading: Boolean? = null) = ResultLive(this).apply {
    showLoading?.let { this.showLoading = it }
}