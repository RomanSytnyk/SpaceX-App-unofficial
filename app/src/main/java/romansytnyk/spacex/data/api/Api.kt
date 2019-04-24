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

    override fun fetchAllPastLaunches(): Deferred<Response<List<Launch>>> {
        return requests.fetchAllPastLaunches()
    }

    override fun fetchFutureLaunches(): Deferred<Response<List<Launch>>> {
        return requests.fetchFutureLaunches()
    }

    override fun fetchRocketList(): Deferred<Response<List<Rocket>>> {
        return requests.fetchRocketList()
    }

    override fun fetchRocketByName(name: String): Deferred<Response<Rocket>> {
        return requests.fetchRocketByName(name)
    }

    override fun fetchCapsuleList(): Deferred<Response<List<Capsule>>> {
        return requests.fetchCapsuleList()
    }

    override fun fetchCapsuleByName(name: String): Deferred<Response<Capsule>> {
        return requests.fetchCapsuleByName(name)
    }
}