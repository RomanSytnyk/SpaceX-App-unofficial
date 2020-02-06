package romansytnyk.spacex.data.mapper

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import romansytnyk.spacex.data.db.entity.Core
import romansytnyk.spacex.data.db.entity.Payload

class RoomTypeConverters {

    @TypeConverter
    fun fromCoreList(value: List<Core>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Core>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toCoreList(value: String): List<Core> {
        val gson = Gson()
        val type = object : TypeToken<List<Core>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromPayloadList(value: List<Payload>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Payload>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toPayloadList(value: String): List<Payload> {
        val gson = Gson()
        val type = object : TypeToken<List<Payload>>() {}.type
        return gson.fromJson(value, type)
    }

    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString("$%$")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split("$%$")
    }
}