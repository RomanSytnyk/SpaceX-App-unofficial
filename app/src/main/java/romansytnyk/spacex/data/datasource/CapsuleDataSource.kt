package romansytnyk.spacex.data.datasource

import romansytnyk.spacex.data.api.Api
import romansytnyk.spacex.data.core.BaseDataSource

class CapsuleDataSource(private val api: Api): BaseDataSource() {
    suspend fun fetchCapsuleList() = getResult { api.fetchCapsuleList() }

    suspend fun fetchCapsuleByName(name: String) = getResult { api.fetchCapsuleByName(name) }
}