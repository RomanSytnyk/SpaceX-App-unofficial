package romansytnyk.spacex.di.modules

import dagger.Module
import dagger.Provides
import romansytnyk.spacex.data.DataManager
import javax.inject.Singleton


/**
 * Created by Roman on 18.02.2018
 */
@Module
class ViewModelModule {
    @Provides
    @Singleton
    internal fun provideDataManager(): DataManager {
        return DataManager()
    }
}