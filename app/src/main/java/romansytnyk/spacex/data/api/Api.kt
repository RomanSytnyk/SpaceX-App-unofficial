package romansytnyk.spacex.data.api


import retrofit2.Response
import romansytnyk.spacex.data.api.core.Requests
import romansytnyk.spacex.data.api.model.Capsule
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.data.api.model.Rocket

/**
 * Created by Roman on 18.02.2018
 */
class Api(private val requests: Requests) : IApi {

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