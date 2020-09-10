package romansytnyk.spacex.data.api.core

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import romansytnyk.spacex.data.api.model.Capsule
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.data.api.model.Rocket

/**
 * Created by Roman on 18.02.2018
 */
interface Requests {
    @GET("/v2/launches")
    suspend fun fetchAllPastLaunches(): Response<List<Launch>>

    @GET("/v2/launches/upcoming")
    suspend fun fetchFutureLaunches(): Response<List<Launch>>

    @GET("/v2/rockets")
    suspend fun fetchRocketList(): Response<List<Rocket>>

    @GET("/v2/rockets/{name}" )
    suspend fun fetchRocketByName(@Path("name") name: String): Response<Rocket>

    @GET("/v2/capsules")
    suspend fun fetchCapsuleList(): Response<List<Capsule>>

    @GET("/v2/capsules/{name}" )
    suspend fun fetchCapsuleByName(@Path("name") name: String): Response<Capsule>
}