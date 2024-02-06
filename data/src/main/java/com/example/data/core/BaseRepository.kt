package com.example.data.core

import com.example.domain.core.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception

internal fun <T> makeNetworkRequest(
    request: suspend () -> T
) =
    flow<Either<T>> {
        request().also {
            emit(Either.Success(it))
        }
    }.flowOn(Dispatchers.IO).catch { exception ->
        emit(Either.Error(Exception(exception.localizedMessage ?: "Unknown error")))
    }