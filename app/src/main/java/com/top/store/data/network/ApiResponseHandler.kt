package com.top.store.data.network

import com.top.store.core.error.ServerException
import retrofit2.Response

abstract class ApiResponseHandler {

    suspend fun <T> handleApiResponse(call: suspend () -> Response<T>): T {
        val res = call()

        if (res.isSuccessful) {
            val body = res.body()
            if (body != null) return body;
        }
        if (res.errorBody() != null) throw ServerException(message = res.errorBody().toString())

        throw ServerException(message = res.message())
    }
}