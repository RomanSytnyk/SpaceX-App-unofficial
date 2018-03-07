package romansytnyk.spacex.ui.base

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

/**
 * Created by Roman on 18.02.2018
 */
interface BaseMvpView : MvpView {
    @StateStrategyType(OneExecutionStateStrategy::class) fun showToast(msg: String)
    @StateStrategyType(OneExecutionStateStrategy::class) fun showSnackbar(msg: String)
    @StateStrategyType(OneExecutionStateStrategy::class) fun showProgressBar()
    @StateStrategyType(OneExecutionStateStrategy::class) fun hideProgressBar()
}