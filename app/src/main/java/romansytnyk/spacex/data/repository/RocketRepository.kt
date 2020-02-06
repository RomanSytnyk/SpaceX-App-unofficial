package romansytnyk.spacex.data.repository

import romansytnyk.spacex.data.core.BaseDataSource
import romansytnyk.spacex.data.core.resultLiveData
import romansytnyk.spacex.data.datasource.RocketDataSource
import romansytnyk.spacex.data.db.dao.RocketDao
import romansytnyk.spacex.data.mapper.DataMapper

class RocketRepository(private val dataSource: RocketDataSource,
                       private val rocketDao: RocketDao): BaseDataSource() {

    val rockets = resultLiveData(
            databaseQuery = { rocketDao.getAll() },
            networkCall = { dataSource.fetchRocketList() },
            saveCallResult = {
                rocketDao.deleteAll()
                rocketDao.insert(it.map { DataMapper.mapRocketToRocketEntity(it) })
            })
}