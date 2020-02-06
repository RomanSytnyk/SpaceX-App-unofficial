package romansytnyk.spacex.data.datasource

import romansytnyk.spacex.data.api.Api
import romansytnyk.spacex.data.core.BaseDataSource

class LaunchesDataSource(private val api: Api): BaseDataSource() {
    suspend fun fetchPastLaunches() = getResult { api.fetchAllPastLaunches() }

    suspend fun fetchFutureLaunches() = getResult { api.fetchFutureLaunches() }
}