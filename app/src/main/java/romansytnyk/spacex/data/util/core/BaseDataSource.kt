package romansytnyk.spacex.data.util.core

import retrofit2.Response
import java.net.UnknownHostException

/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {

    suspend fun <T> getResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                return if (body != null) {
                    Resource.Success(body)
                } else {
                    error(ErrorType.ServerError("${response.code()} ${response.message()}"))
                }
            }
            return error(ErrorType.ServerError("${response.code()} ${response.message()}"))
        } catch (e: UnknownHostException) {
            return error(ErrorType.InternetError())
        } catch (e: Exception) {
            return error(ErrorType.ServerError(e.message ?: e.toString()))
        }
    }

    private fun <T> error(errorType: ErrorType): Resource<T> {
        return Resource.Error(errorType)
    }

}