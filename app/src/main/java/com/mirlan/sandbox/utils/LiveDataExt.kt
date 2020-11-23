package com.mirlan.sandbox.utils

import android.widget.ListAdapter
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView

inline fun <T> LiveData<T>.observe(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<T> {
    val wrappedObserver = Observer<T> { t -> onChanged.invoke(t) }
    observe(owner, wrappedObserver)
    return wrappedObserver
}

fun <R : Any, E> LiveData<Result<R>>.observeResult(
    view: Result,
    adapter: ListAdapter<E, RecyclerView.ViewHolder>? = null,
    also: (Result<R>) -> Unit = {}
) = observe {
    view.setResult(it)
    if(adapter !=  null && it.data is List<*>)
        adapter.submitList(it.data as? List<E>)
    also(it)
}
