package com.mirlan.sandbox.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DiffUtil
import com.mirlan.sandbox.data.vo.Resource
import com.mirlan.sandbox.domain.entity.Salon
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}

fun <T> diffItemCallback(
    contentsSame: (Pair<T, T>) -> Boolean = { it.first == it.second },
    itemsSame: (Pair<T, T>) -> Boolean
) = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = itemsSame(Pair(oldItem, newItem))
    override fun areContentsTheSame(oldItem: T, newItem: T) = contentsSame(Pair(oldItem, newItem))
}

fun <T> Fragment.observe(liveData: LiveData<T>, block: (T) -> Unit) {
    liveData.observe(viewLifecycleOwner, Observer(block))
}

fun <T> Fragment.launch(uiState: StateFlow<T>, function: (T) -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch { uiState.collect { function(it) } }
}

@Suppress("UNCHECKED_CAST")
fun <T> View?.findViewByIdLast(@IdRes resId: Int): T? {
    if (this == null) return null
    if (id == resId) return this as? T
    if (this !is ViewGroup) return null
    for (i in childCount - 1 downTo 0) {
        children.elementAt(i).findViewByIdLast<T>(resId)?.let {
            return it
        }
    }
    return null
}