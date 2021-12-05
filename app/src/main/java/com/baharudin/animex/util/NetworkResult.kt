package com.baharudin.animex.util

sealed class NetworkResult<T> (
    var data : T? = null,
    var messege : String? = null
) {
        class Success<T>(data: T?) : NetworkResult<T>(data)
        class Error<T>(messege: String?, data: T? = null) : NetworkResult<T>(data, messege)
        class Loading<T> : NetworkResult<T>()
}
