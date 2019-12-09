package romansytnyk.spacex.data.api

import retrofit2.Response
import romansytnyk.spacex.data.api.model.Capsule
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.data.api.model.Rocket

/**
 * Created by Roman on 18.02.2018
 */
interface IApi {
    suspend fun fetchAllPastLaunches(): Response<List<Launch>>
    suspend fun fetchFutureLaunches(): Response<List<Launch>>
    suspend fun fetchRocketList(): Response<List<Rocket>>
    suspend fun fetchRocketByName(name: String): Response<Rocket>
    suspend fun fetchCapsuleList(): Response<List<Capsule>>
    suspend fun fetchCapsuleByName(name: String): Response<Capsule>
}