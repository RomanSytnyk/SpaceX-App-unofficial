package romansytnyk.spacex.ui.launches.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import romansytnyk.spacex.data.DataManager
import romansytnyk.spacex.data.api.model.LaunchesBunch
import romansytnyk.spacex.data.api.util.DataWrapper
import romansytnyk.spacex.data.api.util.Failure

/**
 * Created by Roman on 27.02.2018
 */
class LaunchesViewModel(private val dataManager: DataManager) : ViewModel() {
    var data: MutableLiveData<DataWrapper<LaunchesBunch>> = MutableLiveData()

    fun fetchLaunches(): MutableLiveData<DataWrapper<LaunchesBunch>> {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val futureLaunches = dataManager.fetchFutureLaunches().await()
                val pastLaunches = dataManager.fetchAllPastLaunches().await()

                if (futureLaunches.isSuccessful && pastLaunches.isSuccessful) {
                    data.postValue(DataWrapper(LaunchesBunch(futureLaunches.body(), pastLaunches.body())))
                } else if (futureLaunches.isSuccessful) {
                    data.postValue(DataWrapper(LaunchesBunch(futureLaunches.body(), listOf())))
                } else if (pastLaunches.isSuccessful) {
                    data.postValue(DataWrapper(LaunchesBunch(listOf(), pastLaunches.body())))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                data.postValue(DataWrapper(error = Failure.NetworkConnection()))
            }
        }

        return data
    }
}