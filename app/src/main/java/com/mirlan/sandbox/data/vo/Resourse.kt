package com.mirlan.sandbox.data.vo
/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String? = null,
    val error: Throwable? = null
) {
    companion object {
        fun <T> success(data: T?) = Resource(Status.SUCCESS, data, null)

        fun <T> error(
            errorMsg: String? = null,
            error: Throwable? = null,
            data: T? = null
        ) = Resource(Status.ERROR, data, errorMsg, error)

        fun <T> message(msg: String) = Resource(Status.MESSAGE, null, msg)

        fun <T> loading(data: T? = null) = Resource(Status.LOADING, data)
    }
}