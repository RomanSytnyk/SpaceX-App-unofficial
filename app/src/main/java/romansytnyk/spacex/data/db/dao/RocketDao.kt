package romansytnyk.spacex.data.db.dao

import androidx.room.*
import romansytnyk.spacex.data.db.entity.RocketEntity

@Dao
interface RocketDao {
        @Query("SELECT * FROM rocketentity")
        fun getAll(): List<RocketEntity>

        @Insert
        fun insertAll(vararg rocket: RocketEntity)

        @Delete
        fun delete(rocket: RocketEntity)

        @Update
        fun update(vararg rockets: RocketEntity)
}