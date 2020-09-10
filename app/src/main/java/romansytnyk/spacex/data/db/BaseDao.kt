package romansytnyk.spacex.data.db

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(list: List<T>)

    @Update
    fun update(obj: T)

    @Update
    fun update(list: List<T>)

    @Delete
    fun delete(obj: T)

    @Delete
    fun delete(list: List<T>)
}