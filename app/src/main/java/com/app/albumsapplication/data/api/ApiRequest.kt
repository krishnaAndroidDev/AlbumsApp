package com.app.albumsapplication.data.api

import com.app.albumsapplication.ApiException
import retrofit2.Response
import java.io.IOException

object ApiRequest {

    private const val API_FAIL_MESSAGE = "Something went wrong, Please try again later"

    suspend fun <T : Any?> apiRequest(call: suspend () -> Response<T>): T {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                return response.body()!!
            } else {
                throw ApiException(API_FAIL_MESSAGE)
            }
        } catch (e: Exception) {
            throw IOException(e.message.toString())
        }
    }

}