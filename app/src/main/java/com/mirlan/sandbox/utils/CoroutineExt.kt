package com.mirlan.sandbox.utils

import androidx.lifecycle.*
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

fun <T> CoroutineScope.asyncLive(
    context: CoroutineContext = coroutineContext,
    source: suspend CoroutineScope.() -> LiveData<T>
): LiveData<T> {
    val mediatorLiveData = MediatorLiveData<T>()
    launch(context) { mediatorLiveData.addSource(source()) { mediatorLiveData.value = it } }
    return mediatorLiveData
}

fun <T> CoroutineScope.suspendLive(
    context: CoroutineContext = coroutineContext,
    block: suspend CoroutineScope.() -> T
): LiveData<T> {
    val mutableLiveData = MutableLiveData<T>()
    launch(context) { mutableLiveData.value = block() }
    return mutableLiveData
}
//fun CoroutineScope.load(
//    context: CoroutineContext = EmptyCoroutineContext,
//    start: CoroutineStart = CoroutineStart.DEFAULT,
//    block: suspend CoroutineScope.() -> Unit
//): Job {
//    //Constants.progressDialog?.show()
//    val job = launch(context, start, block)
//    job.invokeOnCompletion { //Constants.progressDialog?.dismiss() }
//    return job
//}
suspend fun <T> parentScope(block: suspend CoroutineScope.() -> T) =
    with(CoroutineScope(coroutineContext + SupervisorJob())) { block() }