package romansytnyk.spacex.ui.rockets

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
class RocketsPresenter : MvpPresenter<IRocketsView>(), IRocketsPresenter {
    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var pool: CoroutineContextProvider

    init {
        App.presenterComponent.inject(this)
    }

    override fun fetchLaunches() {
        viewState.showProgressBar()
        async(pool.UI) {
            try {
                val rockets = withContext(pool.IO) { dataManager.fetchRocketList().await() }

                if (rockets.isSuccessful) {
                    viewState.showRockets(rockets.body())
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