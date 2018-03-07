package romansytnyk.spacex.ui.rockets

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import romansytnyk.spacex.data.api.model.Rocket
import romansytnyk.spacex.ui.base.BaseMvpView

/**
 * Created by Roman on 27.02.2018
 */
interface IRocketsView : BaseMvpView {
    @StateStrategyType(AddToEndSingleStrategy::class) fun showRockets(rockets: List<Rocket>?)
    @StateStrategyType(OneExecutionStateStrategy::class) fun showErrorToast()
}