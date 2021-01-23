package com.mobile.moviegallery.data.repositories

import com.mobile.moviegallery.data.api.Result

sealed class Resource<out T: Any> {
    object Loading : Resource<Nothing>()
    sealed class Success<out T: Any> : Resource<T>() {
        class Data<out T: Any>(val data: T) : Success<T>()
        object Empty : Success<Nothing>()
    }
    class Error(val error: String): Resource<Nothing>()
}

fun <T: Any> Result<T>.toResource(): Resource<T> {
    return when (this.status) {
        Result.Status.LOADING -> Resource.Loading
        Result.Status.SUCCESS -> Resource.Success.Data(this.data!!)
        Result.Status.ERROR -> Resource.Error(this.message ?: "Unknown Error")
    }.exhaustive
}

val <T> T.exhaustive: T
    get() = this

fun <T: Any> Resource<T>.doOnError(block: (error: String) -> Unit) {
    if (this is Resource.Error) {
        block(error)
    }
}

fun <T: Any> Resource<T>.doOnSuccess(block: () -> Unit) {
    if (this is Resource.Success) {
        block()
    }
}

fun <T: Any> Resource<T>.doOnSuccessWithData(block: (T) -> Unit) {
    if (this is Resource.Success.Data) {
        block(data)
    }
}

fun <T: Any> Resource<T>.doOnLoading(block: () -> Unit) {
    if (this is Resource.Loading) {
        block()
    }
}