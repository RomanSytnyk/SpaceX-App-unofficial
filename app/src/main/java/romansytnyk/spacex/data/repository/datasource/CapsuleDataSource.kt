package romansytnyk.spacex.data.repository.datasource

import romansytnyk.spacex.data.api.Api
import romansytnyk.spacex.data.util.core.BaseDataSource

class CapsuleDataSource(private val api: Api): BaseDataSource() {
    suspend fun fetchCapsuleList() = getResult { api.fetchCapsuleList() }

    suspend fun fetchCapsuleByName(name: String) = getResult { api.fetchCapsuleByName(name) }
}