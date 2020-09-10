package romansytnyk.spacex.ui.rockets

import androidx.lifecycle.ViewModel
import romansytnyk.spacex.data.repository.RocketRepository

/**
 * Created by Roman on 27.02.2018
 */
class RocketsViewModel(repository: RocketRepository) : ViewModel() {
    val rockets = repository.rockets
}