package com.mirlan.sandbox.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil

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