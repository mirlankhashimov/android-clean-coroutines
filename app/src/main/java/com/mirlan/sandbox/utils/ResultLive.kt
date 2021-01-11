package com.mirlan.sandbox.utils

import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import com.mirlan.sandbox.utils.custom_network.ResultController
import com.mirlan.sandbox.utils.custom_network.ResultFlow
import kotlinx.coroutines.FlowPreview

@FlowPreview
class ResultLive<T>(private val resultFlow: ResultFlow<T>) : PrivateLiveData<Result<T>>(),
    ResultController {
    private val resultLive = resultFlow.asLiveData()
    private val observer = Observer<Result<T>>(::set)
    override fun refresh() = resultFlow.refresh()
    override var showLoading: Boolean
        get() = resultFlow.showLoading
        set(value) = resultFlow::showLoading.set(value)

    override fun onActive() {
        super.onActive()
        resultLive.observeForever(observer)
    }

    override fun onInactive() {
        super.onInactive()
        resultLive.removeObserver(observer)
    }
}

@FlowPreview
fun <T> ResultFlow<T>.toResultLive(showLoading: Boolean? = null) = ResultLive(this).apply {
    showLoading?.let { this.showLoading = it }
}