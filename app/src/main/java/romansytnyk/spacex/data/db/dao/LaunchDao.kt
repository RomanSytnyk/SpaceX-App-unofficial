package romansytnyk.spacex.data.db.dao

import androidx.room.*
import romansytnyk.spacex.data.db.entity.LaunchEntity

@Dao
interface LaunchDao {
    @Query("SELECT * FROM launchentity")
    fun getAll(): List<LaunchEntity>

    @Insert
    fun insertAll(vararg launch: LaunchEntity)

    @Delete
    fun delete(launch: LaunchEntity)

    @Update
    fun update(vararg launches: LaunchEntity)
}