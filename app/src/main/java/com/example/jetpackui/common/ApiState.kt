package com.example.jetpackui.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform

/**
 * Created by Asieuzzaman Wasir on 12,January,2024
 */

sealed class ApiState<out T> {
    data class Success<out R>(val data: R) : ApiState<R>()
    data class Failure(val errorMessage: Throwable) : ApiState<Nothing>()
    data object Loading : ApiState<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success -> "Success $data"
            is Failure -> "Failure ${errorMessage.message}"
            Loading -> "Loading"
        }
    }
}

fun <T, R> ApiState<T>.map(transform: (T) -> (R)): ApiState<R> {
    return when (this) {
        is ApiState.Success -> ApiState.Success(transform(data))
        is ApiState.Failure -> ApiState.Failure(errorMessage)
        ApiState.Loading -> ApiState.Loading
    }
}

fun <T> Flow<ApiState<T>>.onSuccess(action: suspend (T) -> Unit): Flow<ApiState<T>> =
    transform { result ->
        if (result is ApiState.Success) {
            action(result.data)
        }
        return@transform emit(result)
    }

fun <T> Flow<ApiState<T>>.onFailure(action: suspend (Throwable) -> Unit): Flow<ApiState<T>> =
    transform { result ->
        if (result is ApiState.Failure) {
            action(result.errorMessage)
        }
        return@transform emit(result)
    }


fun <T> Flow<ApiState<T>>.onLoading(action: suspend () -> Unit): Flow<ApiState<T>> =
    transform { result ->
        if (result is ApiState.Loading) {
            action()
        }
        return@transform emit(result)
    }