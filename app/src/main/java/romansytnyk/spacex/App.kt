package romansytnyk.spacex

import android.app.Application
import org.koin.android.ext.android.startKoin
import romansytnyk.spacex.di.diModule

/**
 * Created by Roman on 27.02.2018
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(diModule))
    }
}