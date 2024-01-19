package uz.domain

sealed class Result<T> {

    data class Success<T>(val data: T? = null) : Result<T>()

    data class Error<T>(val errorMessage: String?) : Result<T>()
}