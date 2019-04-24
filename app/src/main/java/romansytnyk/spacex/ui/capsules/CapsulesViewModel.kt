package romansytnyk.spacex.ui.capsules

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import romansytnyk.spacex.data.DataManager
import romansytnyk.spacex.data.api.model.Capsule
import romansytnyk.spacex.data.api.util.DataWrapper
import romansytnyk.spacex.data.api.util.Failure

/**
 * Created by Roman on 27.02.2018
 */
class CapsulesViewModel(private val dataManager: DataManager) : ViewModel() {
    var data: MutableLiveData<DataWrapper<List<Capsule>>> = MutableLiveData()

    fun fetchCapsules(): MutableLiveData<DataWrapper<List<Capsule>>> {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val rockets = dataManager.fetchCapsuleList().await()

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