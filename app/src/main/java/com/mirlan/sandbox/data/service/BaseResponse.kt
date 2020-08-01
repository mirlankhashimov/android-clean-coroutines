package com.mirlan.sandbox.data.service

import androidx.annotation.Keep

@Keep
open class BaseResponse<T>(
    /**
     * For success mapped to [T]
     */
    val data: T?,
    val message: String
)