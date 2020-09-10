package romansytnyk.spacex.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import romansytnyk.spacex.data.db.BaseDao
import romansytnyk.spacex.data.db.entity.CapsuleEntity

@Dao
interface CapsuleDao : BaseDao<CapsuleEntity> {

    @Query("SELECT * FROM capsuleentity")
    fun getAll(): LiveData<List<CapsuleEntity>>

}