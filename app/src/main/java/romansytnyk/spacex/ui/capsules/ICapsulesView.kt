package romansytnyk.spacex.ui.capsules

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import romansytnyk.spacex.data.api.model.Capsule
import romansytnyk.spacex.ui.base.BaseMvpView

/**
 * Created by Roman on 27.02.2018
 */
interface ICapsulesView : BaseMvpView {
    @StateStrategyType(AddToEndSingleStrategy::class) fun showCapsules(capsules: List<Capsule>?)
    @StateStrategyType(OneExecutionStateStrategy::class) fun showErrorToast()
}