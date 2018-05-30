package romansytnyk.spacex.ui.capsules

import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.withContext
import romansytnyk.spacex.App
import romansytnyk.spacex.data.DataManager
import romansytnyk.spacex.data.api.model.Capsule
import romansytnyk.spacex.data.api.util.DataWrapper
import romansytnyk.spacex.data.api.util.Failure
import romansytnyk.spacex.util.CoroutineContextProvider
import javax.inject.Inject


/**
 * Created by Roman on 27.02.2018
 */
class CapsulesViewModel : ViewModel() {
    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var pool: CoroutineContextProvider
    var data: MutableLiveData<DataWrapper<List<Capsule>>> = MutableLiveData()

    init {
        App.viewModelComponent.inject(this)
    }

    fun fetchCapsules(): MutableLiveData<DataWrapper<List<Capsule>>> {
        async(pool.UI) {
            try {
                val rockets = withContext(pool.IO) { dataManager.fetchCapsuleList().await() }

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