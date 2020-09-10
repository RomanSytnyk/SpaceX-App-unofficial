package romansytnyk.spacex.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import romansytnyk.spacex.data.db.BaseDao
import romansytnyk.spacex.data.db.entity.RocketEntity

@Dao
interface RocketDao : BaseDao<RocketEntity> {

    @Query("SELECT * FROM rocketentity")
    fun getAll(): LiveData<List<RocketEntity>>

}