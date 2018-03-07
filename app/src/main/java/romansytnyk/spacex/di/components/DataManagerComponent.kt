package romansytnyk.spacex.di.components

import dagger.Component
import romansytnyk.spacex.data.api.Api
import romansytnyk.spacex.di.modules.DataModule
import javax.inject.Singleton


/**
 * Created by Roman on 18.02.2018
 */
@Singleton
@Component(modules = [(DataModule::class)])
interface DataManagerComponent {
    fun api(): Api
}
