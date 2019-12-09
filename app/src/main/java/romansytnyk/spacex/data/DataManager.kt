package romansytnyk.spacex.data


import retrofit2.Response
import romansytnyk.spacex.data.api.Api
import romansytnyk.spacex.data.api.model.Capsule
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.data.api.model.Rocket

/**
 * Created by Roman on 18.02.2018
 */
class DataManager(private val api: Api) : IDataManager {

    override suspend fun fetchAllPastLaunches(): Response<List<Launch>> {
        return api.fetchAllPastLaunches()
    }

    override suspend fun fetchFutureLaunches():  Response<List<Launch>> {
        return api.fetchFutureLaunches()
    }

    override suspend fun fetchRocketList(): Response<List<Rocket>> {
        return api.fetchRocketList()
    }

    override suspend fun fetchRocketByName(name: String): Response<Rocket> {
        return api.fetchRocketByName(name)
    }

    override suspend fun fetchCapsuleList(): Response<List<Capsule>> {
        return api.fetchCapsuleList()
    }

    override suspend fun fetchCapsuleByName(name: String): Response<Capsule> {
        return api.fetchCapsuleByName(name)
    }
}