package com.mirlan.sandbox.utils.custom_network

import com.mirlan.sandbox.utils.Result
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.AbstractFlow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.suspendCancellableCoroutine
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception
import kotlin.coroutines.resume

interface ResultController {
    fun refresh(): Boolean
    var showLoading: Boolean
}

@FlowPreview
class ResultFlow<T>(private var call: Call<T>) : AbstractFlow<Result<T>>(), ResultController {
    private val refresher = Channel<Unit>(Channel.CONFLATED)
    override fun refresh() = try {
        refresher.offer(Unit)
    } catch (e: Exception) {
        Timber.e(e)
        false
    }

    override var showLoading = true

    override suspend fun collectSafely(collector: FlowCollector<Result<T>>) {
        refresh()
        for (item in refresher) {
            if (showLoading) collector.emit(Result.Loading)
            collector.emit(suspendCancellableCoroutine {
                val callback = object : Callback<T> {
                    override fun onFailure(call: Call<T>, t: Throwable) {
                        Timber.e(t)
                        it.resume(Result.Error(Exception(t)))
                    }

                    override fun onResponse(call: Call<T>, response: Response<T>) {
                        try {
                            val body = response.body()!!
                            if (body is List<*> && body.isNullOrEmpty()) it.resume(Result.Empty)
                            else it.resume(Result.Success(body))
                        } catch (e: Exception) {
                            Timber.e(e)
                            it.resume(Result.Error(e))
                        }
                    }
                }
                if (call.isCanceled || call.isExecuted) call = call.clone()
                call.enqueue(callback)
                it.invokeOnCancellation { call.cancel() }
            })
        }
    }

}