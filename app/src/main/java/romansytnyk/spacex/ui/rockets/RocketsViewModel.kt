package romansytnyk.spacex.ui.rockets

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.withContext
import romansytnyk.spacex.App
import romansytnyk.spacex.data.DataManager
import romansytnyk.spacex.data.api.model.Rocket
import romansytnyk.spacex.data.api.util.DataWrapper
import romansytnyk.spacex.data.api.util.Failure
import romansytnyk.spacex.util.CoroutineContextProvider
import javax.inject.Inject

/**
 * Created by Roman on 27.02.2018
 */
class RocketsViewModel : ViewModel() {
    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var pool: CoroutineContextProvider
    var data: MutableLiveData<DataWrapper<List<Rocket>>> = MutableLiveData()

    init {
        App.viewModelComponent.inject(this)
    }

    fun fetchLaunches(): MutableLiveData<DataWrapper<List<Rocket>>> {
        async(pool.UI) {
            try {
                val rockets = withContext(pool.IO) { dataManager.fetchRocketList().await() }

                if (rockets.isSuccessful) {
                    data.value = DataWrapper(rockets.body())
                } else {
                    data.value = DataWrapper(error = Failure.ServerError(rockets.message()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                data.value = DataWrapper(error = Failure.NetworkConnection())
            }
        }

        return data
    }
}