package br.com.wobbu.restcountries.data

import io.reactivex.annotations.NonNull

class ApiResponse {
    var status: Status
    var data: Any?
    var error: Throwable?

    constructor(status: Status, data: Any?, error: Throwable?) {
        this.status = status
        this.data = data
        this.error = error
    }

    companion object {
        fun loading(): ApiResponse {
            return ApiResponse(Status.LOADING, null, null)
        }

        fun success(@NonNull data: Any): ApiResponse {
            return ApiResponse(Status.SUCCESS, data, null)
        }

        fun error(@NonNull error: Throwable): ApiResponse {
            return ApiResponse(Status.ERROR, null, error)
        }
    }
}
