package romansytnyk.spacex.ui.launches.list

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
class LaunchesPresenter : MvpPresenter<ILaunchesView>(), ILaunchesPresenter {
    @Inject lateinit var dataManager: DataManager
    @Inject lateinit var pool: CoroutineContextProvider

    init {
        App.presenterComponent.inject(this)
    }

    override fun fetchLaunches() {
        viewState.showProgressBar()
        async(pool.UI) {
            try {
                val futureLaunches = withContext(pool.IO) { dataManager.fetchFutureLaunches().await() }
                val pastLaunches = withContext(pool.IO) { dataManager.fetchAllPastLaunches().await() }

                if (futureLaunches.isSuccessful && pastLaunches.isSuccessful) {
                    viewState.showLaunches(futureLaunches.body(), pastLaunches.body())
                } else if (futureLaunches.isSuccessful) {
                    viewState.showLaunches(futureLaunches.body(), listOf())
                    viewState.showToast(pastLaunches.message())
                } else if (pastLaunches.isSuccessful) {
                    viewState.showLaunches(listOf(), pastLaunches.body())
                    viewState.showToast(futureLaunches.message())
                }
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.showErrorToast()
            }
            viewState.hideProgressBar()
        }
    }
}