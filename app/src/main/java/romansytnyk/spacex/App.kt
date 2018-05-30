package romansytnyk.spacex

import android.app.Application
import romansytnyk.spacex.di.components.DaggerDataManagerComponent
import romansytnyk.spacex.di.components.DaggerViewModelComponent
import romansytnyk.spacex.di.components.DataManagerComponent
import romansytnyk.spacex.di.components.ViewModelComponent

/**
 * Created by Roman on 27.02.2018
 */

class App : Application() {

    companion object {
        lateinit var dataManagerComponent: DataManagerComponent
        lateinit var viewModelComponent: ViewModelComponent
    }

    override fun onCreate() {
        super.onCreate()
        dataManagerComponent = DaggerDataManagerComponent
                .builder()
                .build()
        viewModelComponent = DaggerViewModelComponent
                .builder()
                .build()
    }
}