package romansytnyk.spacex.data


import kotlinx.coroutines.Deferred
import retrofit2.Response
import romansytnyk.spacex.data.api.Api
import romansytnyk.spacex.data.api.model.Capsule
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.data.api.model.Rocket

/**
 * Created by Roman on 18.02.2018
 */
class DataManager(private val api: Api) : IDataManager {

    override fun fetchAllPastLaunches(): Deferred<Response<List<Launch>>> {
        return api.fetchAllPastLaunches()
    }

    override fun fetchFutureLaunches():  Deferred<Response<List<Launch>>> {
        return api.fetchFutureLaunches()
    }

    override fun fetchRocketList(): Deferred<Response<List<Rocket>>> {
        return api.fetchRocketList()
    }

    override fun fetchRocketByName(name: String): Deferred<Response<Rocket>> {
        return api.fetchRocketByName(name)
    }

    override fun fetchCapsuleList(): Deferred<Response<List<Capsule>>> {
        return api.fetchCapsuleList()
    }

    override fun fetchCapsuleByName(name: String): Deferred<Response<Capsule>> {
        return api.fetchCapsuleByName(name)
    }
}