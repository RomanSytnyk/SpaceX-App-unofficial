package romansytnyk.spacex.ui.launches.list

import androidx.lifecycle.ViewModel
import romansytnyk.spacex.data.repository.LaunchesRepository

/**
 * Created by Roman on 27.02.2018
 */
class LaunchesViewModel(repository: LaunchesRepository) : ViewModel() {
    val pastLaunches = repository.pastLaunches
    val futureLaunches = repository.futureLaunches
}