package romansytnyk.spacex.data.repository

import romansytnyk.spacex.data.core.BaseDataSource
import romansytnyk.spacex.data.core.resultLiveData
import romansytnyk.spacex.data.datasource.CapsuleDataSource
import romansytnyk.spacex.data.db.dao.CapsuleDao
import romansytnyk.spacex.data.mapper.DataMapper

class CapsuleRepository(private val dataSource: CapsuleDataSource,
                        private val capsuleDao: CapsuleDao): BaseDataSource() {

    val rockets = resultLiveData(
            databaseQuery = { capsuleDao.getAll() },
            networkCall = { dataSource.fetchCapsuleList() },
            saveCallResult = {
                capsuleDao.deleteAll()
                capsuleDao.insert(it.map { DataMapper.mapCapsuleToCapsuleEntity(it) })
            })
}