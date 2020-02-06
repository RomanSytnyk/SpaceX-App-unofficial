package romansytnyk.spacex.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import romansytnyk.spacex.data.db.entity.LaunchEntity

@Dao
interface LaunchDao {
    @Query("SELECT * FROM launchentity")
    fun getAll(): LiveData<List<LaunchEntity>>

    @Query("SELECT * FROM launchentity WHERE isPastLaunch = 1")
    fun getPastLaunches(): LiveData<List<LaunchEntity>>

    @Query("SELECT * FROM launchentity WHERE isPastLaunch = 0")
    fun getFutureLaunches(): LiveData<List<LaunchEntity>>

    @Insert
    fun insert(launches: List<LaunchEntity>)

    @Delete
    fun delete(launch: LaunchEntity)

    @Query("DELETE FROM launchentity")
    fun deleteAllTable()

    @Query("DELETE FROM launchentity WHERE isPastLaunch = 1")
    fun deleteAllPastLaunches()

    @Query("DELETE FROM launchentity WHERE isPastLaunch = 0")
    fun deleteAllFutureLaunches()

    @Update
    fun update(vararg launches: LaunchEntity)
}