package romansytnyk.spacex.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import romansytnyk.spacex.data.db.dao.CapsuleDao
import romansytnyk.spacex.data.db.dao.LaunchDao
import romansytnyk.spacex.data.db.dao.RocketDao
import romansytnyk.spacex.data.db.entity.CapsuleEntity
import romansytnyk.spacex.data.db.entity.LaunchEntity
import romansytnyk.spacex.data.db.entity.RocketEntity
import romansytnyk.spacex.data.mapper.RoomTypeConverters

@Database(entities = [CapsuleEntity::class, LaunchEntity::class, RocketEntity::class],
        version = 1)
@TypeConverters(RoomTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun rocketDao(): RocketDao
    abstract fun launchDao(): LaunchDao
    abstract fun capsuleDao(): CapsuleDao

    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(context,
                AppDatabase::class.java, "app.db")
                .build()
    }
}