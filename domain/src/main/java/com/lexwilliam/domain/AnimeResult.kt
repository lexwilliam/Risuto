package com.lexwilliam.domain

sealed class AnimeResult<out T> {
    data class Success<out T>(val response: T) : AnimeResult<T>()
    data class Fail(val exception: Exception) : AnimeResult<Nothing>()
}

inline fun <T> AnimeResult<T>.doWhen(
    onSuccess: (T) -> Unit,
    onFail: (Exception) -> Unit,
) {
    when (this) {
        is AnimeResult.Success -> onSuccess(response)
        is AnimeResult.Fail -> onFail(exception)
    }
}