package romansytnyk.spacex.di


import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import romansytnyk.spacex.data.api.core.ApiClient
import romansytnyk.spacex.data.datasource.CapsuleDataSource
import romansytnyk.spacex.data.datasource.LaunchesDataSource
import romansytnyk.spacex.data.datasource.RocketDataSource
import romansytnyk.spacex.data.db.AppDatabase
import romansytnyk.spacex.data.repository.*
import romansytnyk.spacex.ui.capsules.CapsulesViewModel
import romansytnyk.spacex.ui.launches.list.LaunchesViewModel
import romansytnyk.spacex.ui.rockets.RocketsViewModel

val diModule = module {
    // Data
    single { ApiClient().spacexApi }

    single { LaunchesDataSource(get()) }
    single { LaunchesRepository(get(), get()) }

    single { RocketDataSource(get()) }
    single { RocketRepository(get(), get()) }

    single { CapsuleDataSource(get()) }
    single { CapsuleRepository(get(), get()) }

    // Database
    single { AppDatabase.buildDatabase(androidApplication()) }
    single { get<AppDatabase>().capsuleDao() }
    single { get<AppDatabase>().launchDao() }
    single { get<AppDatabase>().rocketDao() }

    // ViewModels
    viewModel { CapsulesViewModel(get()) }
    viewModel { RocketsViewModel(get()) }
    viewModel { LaunchesViewModel(get()) }
}