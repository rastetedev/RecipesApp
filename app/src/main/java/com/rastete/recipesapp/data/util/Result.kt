package com.rastete.recipesapp.data.util

sealed class Result<T>(
    val data: T? = null,
    val message: String? = null
) {

    class Success<T>(data: T) : Result<T>(data = data)
    class Error<T>(data: T, message: String?) : Result<T>(data = data, message = message)
    class Loading<T> : Result<T>()
}