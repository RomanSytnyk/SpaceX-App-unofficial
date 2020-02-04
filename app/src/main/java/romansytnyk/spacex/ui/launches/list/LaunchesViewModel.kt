package romansytnyk.spacex.ui.launches.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import romansytnyk.spacex.data.DataManager
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.data.api.util.DataWrapper
import romansytnyk.spacex.data.api.util.Failure

/**
 * Created by Roman on 27.02.2018
 */
class LaunchesViewModel(private val dataManager: DataManager) : ViewModel() {
    var data: MutableLiveData<DataWrapper<List<Launch>>> = MutableLiveData()

    fun fetchPastLaunches() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val pastLaunches = dataManager.fetchAllPastLaunches()

                if (pastLaunches.isSuccessful) {
                    data.postValue(DataWrapper(pastLaunches.body()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                data.postValue(DataWrapper(error = Failure.NetworkConnection()))
            }
        }
    }

    fun fetchFutureLaunches() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val futureLaunches = dataManager.fetchFutureLaunches()

                if (futureLaunches.isSuccessful) {
                    data.postValue(DataWrapper(futureLaunches.body()))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                data.postValue(DataWrapper(error = Failure.NetworkConnection()))
            }
        }
    }
}