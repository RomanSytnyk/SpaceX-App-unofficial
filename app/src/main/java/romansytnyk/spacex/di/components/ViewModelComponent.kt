package romansytnyk.spacex.di.components

import dagger.Component
import romansytnyk.spacex.di.modules.ViewModelModule
import romansytnyk.spacex.ui.capsules.CapsulesViewModel
import romansytnyk.spacex.ui.launches.list.LaunchesViewModel
import romansytnyk.spacex.ui.rockets.RocketsViewModel

import javax.inject.Singleton

/**
 * Created by Roman on 18.02.2018
 */
@Singleton
@Component(modules = [(ViewModelModule::class)])
interface ViewModelComponent {
    fun inject(viewModel: LaunchesViewModel)
    fun inject(viewModel: RocketsViewModel)
    fun inject(viewModel: CapsulesViewModel)
}
