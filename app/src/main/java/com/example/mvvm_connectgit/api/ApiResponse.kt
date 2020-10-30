package com.example.mvvm_connectgit.api

import retrofit2.Response
import timber.log.Timber
import java.io.IOException


class ApiResponse<T>(response: Response<T>?) {
    var code = 0

    var body: T? = null

    var errorMessage: String? = null

    init {
        code = response!!.code()
        if (response!!.isSuccessful()) {
            body = response!!.body()
            errorMessage = null
        } else {
            var message: String? = null
            if (response!!.errorBody() != null) {
                try {
                    message = response!!.errorBody()!!.string()
                } catch (ignored: IOException) {
                    Timber.e(ignored, "error while parsing response")
                }
            }
            if (message == null || message.trim { it <= ' ' }.length == 0) {
                message = response!!.message()
            }
            errorMessage = message
            body = null
        }
    }

    constructor(response: Response<T>?, error: Throwable): this(response) {
        code = 500
        body = null
        errorMessage = error.message
    }

    fun isSuccessful(): Boolean {
        return code >= 200 && code < 300
    }

}