package com.palash.retrofitwithhilt_mvvm.utils

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {
    class Loading<T> : NetworkResult<T>()
    class Success<T>(data: T?) : NetworkResult<T>(data)
    class Error<T>(error: String?) : NetworkResult<T>(null, error)
}
