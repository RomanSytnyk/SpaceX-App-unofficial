package romansytnyk.spacex

import android.app.Application
import romansytnyk.spacex.di.components.DaggerDataManagerComponent
import romansytnyk.spacex.di.components.DaggerPresenterComponent
import romansytnyk.spacex.di.components.DataManagerComponent
import romansytnyk.spacex.di.components.PresenterComponent

/**
 * Created by Roman on 27.02.2018
 */

class App : Application() {

    companion object {
        lateinit var dataManagerComponent: DataManagerComponent
        lateinit var presenterComponent: PresenterComponent
    }

    override fun onCreate() {
        super.onCreate()
        dataManagerComponent = DaggerDataManagerComponent
                .builder()
                .build()
        presenterComponent = DaggerPresenterComponent
                .builder()
                .build()
    }
}