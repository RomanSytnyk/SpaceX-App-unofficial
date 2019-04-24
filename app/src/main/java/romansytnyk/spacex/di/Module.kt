package romansytnyk.spacex.di

import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import romansytnyk.spacex.data.DataManager
import romansytnyk.spacex.data.api.core.ApiClient
import romansytnyk.spacex.ui.capsules.CapsulesViewModel
import romansytnyk.spacex.ui.launches.list.LaunchesViewModel
import romansytnyk.spacex.ui.rockets.RocketsViewModel

val diModule = module {
    single { ApiClient().spacexApi }
    single { DataManager(get()) }

    // ViewModels
    viewModel { CapsulesViewModel(get()) }
    viewModel { RocketsViewModel(get()) }
    viewModel { LaunchesViewModel(get()) }
}