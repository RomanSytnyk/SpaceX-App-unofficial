package romansytnyk.spacex.ui.capsules

import androidx.lifecycle.ViewModel
import romansytnyk.spacex.data.repository.CapsuleRepository

/**
 * Created by Roman on 27.02.2018
 */
class CapsulesViewModel(repository: CapsuleRepository) : ViewModel() {
    val capsules = repository.rockets
}