package romansytnyk.spacex.data.repository

import romansytnyk.spacex.data.util.core.BaseDataSource
import romansytnyk.spacex.data.util.core.resultLiveData
import romansytnyk.spacex.data.repository.datasource.LaunchesDataSource
import romansytnyk.spacex.data.db.dao.LaunchDao
import romansytnyk.spacex.data.util.mapper.DataMapper.mapLaunchToLaunchEntity

class LaunchesRepository(private val dataSource: LaunchesDataSource,
                         private val launchDao: LaunchDao): BaseDataSource() {

    val pastLaunches = resultLiveData(
            databaseQuery = { launchDao.getPastLaunches() },
            networkCall = { dataSource.fetchPastLaunches() },
            saveCallResult = { 
                launchDao.deleteAllPastLaunches()
                launchDao.insert(it.map { mapLaunchToLaunchEntity(it, isPastLaunches = true) })
            })

    val futureLaunches = resultLiveData(
            databaseQuery = { launchDao.getFutureLaunches() },
            networkCall = { dataSource.fetchFutureLaunches() },
            saveCallResult = {
                launchDao.deleteAllFutureLaunches()
                launchDao.insert(it.map { mapLaunchToLaunchEntity(it, isPastLaunches = false) })
            })
}