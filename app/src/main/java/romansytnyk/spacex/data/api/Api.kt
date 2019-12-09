package romansytnyk.spacex.data.api


import kotlinx.coroutines.Deferred
import retrofit2.Response
import romansytnyk.spacex.data.api.core.Requests
import romansytnyk.spacex.data.api.model.Capsule
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.data.api.model.Rocket

/**
 * Created by Roman on 18.02.2018
 */
class Api(private val requests: Requests) : IApi {
    companion object {
        const val REQUEST_GET_ALL_PAST_LAUNCHES = "/v2/launches"
        const val REQUEST_GET_FUTURE_LAUNCHES = "/v2/launches/upcoming"
        const val REQUEST_GET_ROCKETS = "/v2/rockets"
        const val REQUEST_GET_CAPSULES = "/v2/capsules"
    }

    override suspend fun fetchAllPastLaunches(): Response<List<Launch>> {
        return requests.fetchAllPastLaunches()
    }

    override suspend fun fetchFutureLaunches(): Response<List<Launch>> {
        return requests.fetchFutureLaunches()
    }

    override suspend fun fetchRocketList(): Response<List<Rocket>> {
        return requests.fetchRocketList()
    }

    override suspend fun fetchRocketByName(name: String): Response<Rocket> {
        return requests.fetchRocketByName(name)
    }

    override suspend fun fetchCapsuleList(): Response<List<Capsule>> {
        return requests.fetchCapsuleList()
    }

    override suspend fun fetchCapsuleByName(name: String): Response<Capsule> {
        return requests.fetchCapsuleByName(name)
    }
}