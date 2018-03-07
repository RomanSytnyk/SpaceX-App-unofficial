package romansytnyk.spacex.di.modules

import dagger.Module
import dagger.Provides
import romansytnyk.spacex.data.api.Api
import romansytnyk.spacex.data.api.core.ApiClient
import javax.inject.Singleton


/**
 * Created by Roman on 18.02.2018
 */

@Module
class DataModule {
    @Provides
    @Singleton
    internal fun provideApi(): Api {
        return ApiClient().bankApi
    }
}
