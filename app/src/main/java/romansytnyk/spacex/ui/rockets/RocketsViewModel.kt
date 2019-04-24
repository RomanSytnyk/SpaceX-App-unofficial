package romansytnyk.spacex.ui.rockets

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import romansytnyk.spacex.App
import romansytnyk.spacex.data.DataManager
import romansytnyk.spacex.data.api.model.Rocket
import romansytnyk.spacex.data.api.util.DataWrapper
import romansytnyk.spacex.data.api.util.Failure
import javax.inject.Inject

/**
 * Created by Roman on 27.02.2018
 */
class RocketsViewModel : ViewModel() {
    @Inject lateinit var dataManager: DataManager
    var data: MutableLiveData<DataWrapper<List<Rocket>>> = MutableLiveData()

    init {
        App.viewModelComponent.inject(this)
    }

    fun fetchLaunches(): MutableLiveData<DataWrapper<List<Rocket>>> {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val rockets = dataManager.fetchRocketList().await()

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