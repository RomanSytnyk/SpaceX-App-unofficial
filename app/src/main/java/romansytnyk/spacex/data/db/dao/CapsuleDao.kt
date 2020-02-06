package romansytnyk.spacex.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import romansytnyk.spacex.data.db.entity.CapsuleEntity

@Dao
interface CapsuleDao {
    @Query("SELECT * FROM capsuleentity")
    fun getAll(): LiveData<List<CapsuleEntity>>

    @Insert
    fun insert(launches: List<CapsuleEntity>)

    @Query("DELETE FROM capsuleentity")
    fun deleteAll()

    @Delete
    fun delete(capsule: CapsuleEntity)

    @Update
    fun update(vararg capsules: CapsuleEntity)
}