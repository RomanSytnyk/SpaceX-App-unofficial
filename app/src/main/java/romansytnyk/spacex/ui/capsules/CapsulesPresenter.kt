package romansytnyk.spacex.ui.capsules

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.withContext
import romansytnyk.spacex.App
import romansytnyk.spacex.data.DataManager
import romansytnyk.spacex.util.CoroutineContextProvider
import javax.inject.Inject

/**
 * Created by Roman on 27.02.2018
 */
@InjectViewState
class CapsulesPresenter : MvpPresenter<ICapsulesView>(), ICapsulesPresenter {
    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var pool: CoroutineContextProvider

    init {
        App.presenterComponent.inject(this)
    }

    override fun fetchCapsules() {
        viewState.showProgressBar()
        async(pool.UI) {
            try {
                val rockets = withContext(pool.IO) { dataManager.fetchCapsuleList().await() }

                if (rockets.isSuccessful) {
                    viewState.showCapsules(rockets.body())
                } else {
                    viewState.showToast(rockets.message())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.showErrorToast()
            }
            viewState.hideProgressBar()
        }
    }
}