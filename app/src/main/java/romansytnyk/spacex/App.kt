package romansytnyk.spacex

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import romansytnyk.spacex.di.diModule

/**
 * Created by Roman on 27.02.2018
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(diModule)
        }
    }
}