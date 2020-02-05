package romansytnyk.spacex.data.db.dao

import androidx.room.*
import romansytnyk.spacex.data.db.entity.CapsuleEntity

@Dao
interface CapsuleDao {
    @Query("SELECT * FROM capsuleentity")
    fun getAll(): List<CapsuleEntity>

    @Insert
    fun insertAll(vararg capsule: CapsuleEntity)

    @Delete
    fun delete(capsule: CapsuleEntity)

    @Update
    fun update(vararg capsules: CapsuleEntity)
}