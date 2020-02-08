package romansytnyk.spacex.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import romansytnyk.spacex.data.db.BaseDao
import romansytnyk.spacex.data.db.entity.LaunchEntity

@Dao
interface LaunchDao : BaseDao<LaunchEntity> {

    @Query("SELECT * FROM launchentity")
    fun getAll(): LiveData<List<LaunchEntity>>

    @Query("SELECT * FROM launchentity WHERE isPastLaunch = 1")
    fun getPastLaunches(): LiveData<List<LaunchEntity>>

    @Query("SELECT * FROM launchentity WHERE isPastLaunch = 0")
    fun getFutureLaunches(): LiveData<List<LaunchEntity>>

    @Query("DELETE FROM launchentity WHERE isPastLaunch = 1")
    fun deleteAllPastLaunches()

    @Query("DELETE FROM launchentity WHERE isPastLaunch = 0")
    fun deleteAllFutureLaunches()

}