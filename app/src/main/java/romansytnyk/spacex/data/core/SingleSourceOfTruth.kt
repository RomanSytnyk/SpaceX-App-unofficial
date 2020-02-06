package romansytnyk.spacex.data.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers

/**
 * The database serves as the single source of truth.
 * Therefore UI can receive data updates from database only.
 * Function notify UI about:
 * [Resource.Status.SUCCESS] - with data from database
 * [Resource.Status.ERROR] - if error has occurred from any source
 * [Resource.Status.LOADING]
 */
fun <T, A> resultLiveData(databaseQuery: () -> LiveData<T>,
                          networkCall: suspend () -> Resource<A>,
                          saveCallResult: suspend (A) -> Unit): LiveData<Resource<T>> =
        liveData(Dispatchers.IO) {
            emit(Resource.Loading())
            val source: LiveData<Resource<T>> = databaseQuery.invoke().map { Resource.Success(it) }
            emitSource(source)

            val responseStatus = networkCall.invoke()
            if (responseStatus is Resource.Success) {
                saveCallResult(responseStatus.data)
            } else if (responseStatus is Resource.Error) {
                emit(Resource.Error(responseStatus.message))
                emitSource(source)
            }
        }