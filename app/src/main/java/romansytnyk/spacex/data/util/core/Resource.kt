package romansytnyk.spacex.data.util.core

/**
 * A generic class that holds a value with its loading status.
 *
 * Result is usually created by the Repository classes where they return
 * `LiveData<Result<T>>` to pass back the latest data to the UI with its fetch status.
 */

sealed class ErrorType {
    class InternetError : ErrorType()
    data class ServerError(val message: String) : ErrorType()
}


sealed class Resource<out T> {
    class Loading<T> : Resource<T>()
    data class Error<T>(val error: ErrorType) : Resource<T>()
    data class Success<T>(val data: T) : Resource<T>()
}