package com.mirlan.sandbox.utils

import androidx.lifecycle.LiveData

open class PrivateLiveData<T> : LiveData<T>() {
    internal fun post(value: T?) = postValue(value)
    internal fun set(value: T?) = setValue(value)
}