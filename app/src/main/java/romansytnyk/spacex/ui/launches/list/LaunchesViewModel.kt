package romansytnyk.spacex.ui.launches.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.withContext
import romansytnyk.spacex.App
import romansytnyk.spacex.data.DataManager
import romansytnyk.spacex.data.api.model.LaunchesBunch
import romansytnyk.spacex.data.api.util.DataWrapper
import romansytnyk.spacex.data.api.util.Failure
import romansytnyk.spacex.util.CoroutineContextProvider
import javax.inject.Inject

/**
 * Created by Roman on 27.02.2018
 */
class LaunchesViewModel : ViewModel() {
    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var pool: CoroutineContextProvider
    var data: MutableLiveData<DataWrapper<LaunchesBunch>> = MutableLiveData()

    init {
        App.viewModelComponent.inject(this)
    }

    fun fetchLaunches(): MutableLiveData<DataWrapper<LaunchesBunch>> {
        async(pool.UI) {
            try {
                val futureLaunches = withContext(pool.IO) { dataManager.fetchFutureLaunches().await() }
                val pastLaunches = withContext(pool.IO) { dataManager.fetchAllPastLaunches().await() }

                if (futureLaunches.isSuccessful && pastLaunches.isSuccessful) {
                    data.value = DataWrapper(LaunchesBunch(futureLaunches.body(), pastLaunches.body()))
                } else if (futureLaunches.isSuccessful) {
                    data.value = DataWrapper(LaunchesBunch(futureLaunches.body(), listOf()))
                } else if (pastLaunches.isSuccessful) {
                    data.value = DataWrapper(LaunchesBunch(listOf(), pastLaunches.body()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                data.value = DataWrapper(error = Failure.NetworkConnection())
            }
        }

        return data
    }
}