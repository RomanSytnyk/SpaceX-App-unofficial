package romansytnyk.spacex.data.api

import kotlinx.coroutines.Deferred
import retrofit2.Response
import romansytnyk.spacex.data.api.model.Capsule
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.data.api.model.Rocket

/**
 * Created by Roman on 18.02.2018
 */
interface IApi {
    fun fetchAllPastLaunches(): Deferred<Response<List<Launch>>>
    fun fetchFutureLaunches(): Deferred<Response<List<Launch>>>
    fun fetchRocketList(): Deferred<Response<List<Rocket>>>
    fun fetchRocketByName(name: String): Deferred<Response<Rocket>>
    fun fetchCapsuleList(): Deferred<Response<List<Capsule>>>
    fun fetchCapsuleByName(name: String): Deferred<Response<Capsule>>
}