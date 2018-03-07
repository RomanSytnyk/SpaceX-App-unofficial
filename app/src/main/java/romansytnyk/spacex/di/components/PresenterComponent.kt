package romansytnyk.spacex.di.components

import dagger.Component
import romansytnyk.spacex.di.modules.PresenterModule
import romansytnyk.spacex.ui.capsules.CapsulesPresenter
import romansytnyk.spacex.ui.launches.list.LaunchesPresenter
import romansytnyk.spacex.ui.rockets.RocketsPresenter

import javax.inject.Singleton

/**
 * Created by Roman on 18.02.2018
 */
@Singleton
@Component(modules = [(PresenterModule::class)])
interface PresenterComponent {
    fun inject(presenter: LaunchesPresenter)
    fun inject(presenter: RocketsPresenter)
    fun inject(presenter: CapsulesPresenter)
}
