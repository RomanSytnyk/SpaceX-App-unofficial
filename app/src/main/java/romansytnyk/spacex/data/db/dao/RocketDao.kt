package romansytnyk.spacex.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import romansytnyk.spacex.data.db.entity.RocketEntity

@Dao
interface RocketDao {
        @Query("SELECT * FROM rocketentity")
        fun getAll(): LiveData<List<RocketEntity>>

        @Insert
        fun insert(launches: List<RocketEntity>)

        @Query("DELETE FROM rocketentity")
        fun deleteAll()

        @Delete
        fun delete(rocket: RocketEntity)

        @Update
        fun update(vararg rockets: RocketEntity)
}