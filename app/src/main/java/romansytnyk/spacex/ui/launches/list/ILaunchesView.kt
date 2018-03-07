package romansytnyk.spacex.ui.launches.list

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.ui.base.BaseMvpView

/**
 * Created by Roman on 27.02.2018
 */
interface ILaunchesView : BaseMvpView {
    @StateStrategyType(AddToEndSingleStrategy::class) fun showLaunches(futureLaunches: List<Launch>?,
                                                                       pastLaunches: List<Launch>?)
    @StateStrategyType(OneExecutionStateStrategy::class) fun showErrorToast()
}