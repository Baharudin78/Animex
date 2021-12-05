package com.baharudin.animex.data.remote

import com.baharudin.animex.util.NetworkResult
import retrofit2.Response

abstract class BaseApiResponse {
    suspend fun <T> safeApiCall(apiCall : suspend () -> Response<T>) : NetworkResult<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                response.body().let { data ->
                    return NetworkResult.Success(data)
                }
            }
            return error("${response.code()} ${response.message()}")
        }catch (e : Exception) {
            return error(e.message ?: e.toString())
        }
    }
    private fun <T> error (errorMessege : String) : NetworkResult<T> =
        NetworkResult.Error("Api call failed $errorMessege")
}