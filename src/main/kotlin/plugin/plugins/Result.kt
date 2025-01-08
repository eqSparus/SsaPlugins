package com.example.myapplication.plugin.plugins

sealed interface Result<R> {

    data class Success<R>(val r: R) : Result<R>

    data class ThrowFailure<R>(val e: Exception) : Result<R>

    data class Failure<R>(val message: String) : Result<R>

    fun getOrNull(): R? = (this as? Success<R>)?.r

    fun getOrDefault(defaultValue: R): R = (this as? Success<R>)?.r ?: defaultValue

    suspend fun onFail(block: suspend (Result<R>) -> Result<R>): Result<R> =
        if (this is Success) {
            this
        } else {
            block.invoke(this)
        }

    suspend fun onSuccess(block: suspend (Result<R>) -> Result<R>): Result<R> =
        if (this !is Success) {
            block.invoke(this)
        } else {
            this
        }


}
