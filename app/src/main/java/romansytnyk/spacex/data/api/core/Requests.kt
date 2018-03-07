package romansytnyk.spacex.data.api.core

import kotlinx.coroutines.experimental.Deferred
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
    fun fetchAllPastLaunches(): Deferred<Response<List<Launch>>>

    @GET(Api.REQUEST_GET_FUTURE_LAUNCHES)
    fun fetchFutureLaunches(): Deferred<Response<List<Launch>>>

    @GET(Api.REQUEST_GET_ROCKETS)
    fun fetchRocketList(): Deferred<Response<List<Rocket>>>

    @GET(Api.REQUEST_GET_ROCKETS + "/{name}" )
    fun fetchRocketByName(@Path("name") name: String): Deferred<Response<Rocket>>


    @GET(Api.REQUEST_GET_CAPSULES)
    fun fetchCapsuleList(): Deferred<Response<List<Capsule>>>

    @GET(Api.REQUEST_GET_CAPSULES + "/{name}" )
    fun fetchCapsuleByName(@Path("name") name: String): Deferred<Response<Capsule>>
}