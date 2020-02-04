package romansytnyk.spacex.ui.rockets

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import romansytnyk.spacex.data.DataManager
import romansytnyk.spacex.data.api.model.Rocket
import romansytnyk.spacex.data.api.util.DataWrapper
import romansytnyk.spacex.data.api.util.Failure

/**
 * Created by Roman on 27.02.2018
 */
class RocketsViewModel(private val dataManager: DataManager) : ViewModel() {
    var data: MutableLiveData<DataWrapper<List<Rocket>>> = MutableLiveData()

    fun fetchLaunches(): MutableLiveData<DataWrapper<List<Rocket>>> {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val rockets = dataManager.fetchRocketList()

                if (rockets.isSuccessful) {
                    data.postValue(DataWrapper(rockets.body()))
                } else {
                    data.postValue(DataWrapper(error = Failure.ServerError(rockets.message())))
                }
            } catch (e: Exception) {
                e.printStackTrace()
                data.postValue(DataWrapper(error = Failure.NetworkConnection()))
            }
        }

        return data
    }
}