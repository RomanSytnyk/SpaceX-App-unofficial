package romansytnyk.spacex.data.repository.datasource

import romansytnyk.spacex.data.api.Api
import romansytnyk.spacex.data.util.core.BaseDataSource

class RocketDataSource(private val api: Api): BaseDataSource() {
    suspend fun fetchRocketList() = getResult { api.fetchRocketList() }

    suspend fun fetchRocketByName(name: String) = getResult { api.fetchRocketByName(name) }
}