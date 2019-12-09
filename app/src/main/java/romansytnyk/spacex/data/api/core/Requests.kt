package romansytnyk.spacex.data.api.core

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import romansytnyk.spacex.data.api.Api
import romansytnyk.spacex.data.api.model.Capsule
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.data.api.model.Rocket

/**
 * Created by Roman on 18.02.2018
 */
interface Requests {
    @GET(Api.REQUEST_GET_ALL_PAST_LAUNCHES)
    suspend fun fetchAllPastLaunches(): Response<List<Launch>>

    @GET(Api.REQUEST_GET_FUTURE_LAUNCHES)
    suspend fun fetchFutureLaunches(): Response<List<Launch>>

    @GET(Api.REQUEST_GET_ROCKETS)
    suspend fun fetchRocketList(): Response<List<Rocket>>

    @GET(Api.REQUEST_GET_ROCKETS + "/{name}" )
    suspend fun fetchRocketByName(@Path("name") name: String): Response<Rocket>


    @GET(Api.REQUEST_GET_CAPSULES)
    suspend fun fetchCapsuleList(): Response<List<Capsule>>

    @GET(Api.REQUEST_GET_CAPSULES + "/{name}" )
    suspend fun fetchCapsuleByName(@Path("name") name: String): Response<Capsule>
}