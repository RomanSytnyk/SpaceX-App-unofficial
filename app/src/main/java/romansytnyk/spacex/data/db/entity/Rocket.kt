package romansytnyk.spacex.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RocketEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,

        @ColumnInfo(name = "name") val name: String?,
        @ColumnInfo(name = "type") val type: String?,
        @ColumnInfo(name = "active") val active: Boolean?,
        @ColumnInfo(name = "stages") val stages: Int?,
        @ColumnInfo(name = "boosters") val boosters: Int?,
        @ColumnInfo(name = "cost_per_launch") val costPerLaunch: Int?,
        @ColumnInfo(name = "success_rate_pct") val successRatePct: Int?,
        @ColumnInfo(name = "first_flight") val firstFlight: String?,
        @ColumnInfo(name = "country") val country: String?,
        @ColumnInfo(name = "company") val company: String?,
        @Embedded val height: Height?,
        @Embedded val diameter: Diameter?,
        @Embedded val mass: Mass?,
        @Embedded val firstStage: FirstStage?,
        @Embedded val secondStage: SecondStage?,
        @ColumnInfo(name = "description") val description: String?
)


data class Mass(
        @ColumnInfo(name = "mass_kg") val kg: Double?,
        @ColumnInfo(name = "mass_lb") val lb: Double?
)

data class SecondStage(
        @ColumnInfo(name = "second_stage_engines") val engines: Int?,
        @ColumnInfo(name = "second_stage_fuleTons")val fuelAmountTons: Double?,
        @ColumnInfo(name = "second_stage_burnTimeSec")val burnTimeSec: Double?
)

data class Payloads(
        val option1: String?,
        @Embedded val compositeFairing: CompositeFairing?
)

data class CompositeFairing(
        @Embedded val height: Height?,
        @Embedded val diameter: Diameter?
)

data class Height(
        @ColumnInfo(name = "height_meters") val meters: Double?,
        @ColumnInfo(name = "height_feet") val feet: Double?
)

data class FirstStage(
        val reusable: Boolean?,
        val engines: Int?,
        val fuelAmountTons: Double?,
        val burnTimeSec: Double?
)

data class Diameter(
        @ColumnInfo(name ="d_m") val meters: Double?,
        @ColumnInfo(name ="d_f") val feet: Double?
)
