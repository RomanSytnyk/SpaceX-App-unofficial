package romansytnyk.spacex.ui.launches.list

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import romansytnyk.spacex.App
import romansytnyk.spacex.data.DataManager
import romansytnyk.spacex.data.api.model.LaunchesBunch
import romansytnyk.spacex.data.api.util.DataWrapper
import romansytnyk.spacex.data.api.util.Failure
import javax.inject.Inject

/**
 * Created by Roman on 27.02.2018
 */
class LaunchesViewModel : ViewModel() {
    @Inject lateinit var dataManager: DataManager
    var data: MutableLiveData<DataWrapper<LaunchesBunch>> = MutableLiveData()

    init {
        App.viewModelComponent.inject(this)
    }

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