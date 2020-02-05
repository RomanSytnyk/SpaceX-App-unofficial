package romansytnyk.spacex.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CapsuleEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int,

        @ColumnInfo(name = "name") val name: String?,
        @ColumnInfo(name = "type") val type: String?,
        @ColumnInfo(name ="active") val active: Boolean?,
        @ColumnInfo(name ="crew_capacity") val crewCapacity: Int?,
        @ColumnInfo(name ="sidewall_angle_deg") val sidewallAngleDeg: Int?,
        @ColumnInfo(name ="orbit_duration_yr") val orbitDurationYr: Double?,

        @Embedded val heatShield: HeatShield?,
        @Embedded val launchPayloadMass: LaunchPayloadMass?,
        @Embedded val launchPayloadVol: LaunchPayloadVol?,
        @Embedded val returnPayloadMass: ReturnPayloadMass?,
        @Embedded val returnPayloadVol: ReturnPayloadVol?,
        @Embedded val pressurizedCapsule: PressurizedCapsule?,
        @Embedded val trunk: Trunk?,
        @Embedded val heightWTrunk: HeightWTrunk?,
        @Embedded val diameter: Diameter?
)

data class LaunchPayloadMass(
        @ColumnInfo(name ="lp_kg") val kg: Double?,
        @ColumnInfo(name ="lp_lb") val lb: Double?
)

data class HeatShield(
        val material: String?,
        val sizeMeters: Double?,
        val tempDegrees: Double?,
        val devPartner: String?
)

data class LaunchPayloadVol(
        @ColumnInfo(name ="lp_m") val cubicMeters: Double?,
        @ColumnInfo(name ="lp_f") val cubicFeet: Double?
)

data class PressurizedCapsule(
        @Embedded val payloadVolume: PayloadVolume?
)

data class PayloadVolume(
        @ColumnInfo(name ="pv_m") val cubicMeters: Double?,
        @ColumnInfo(name ="pv_f") val cubicFeet: Double?
)

data class HeightWTrunk(
        @ColumnInfo(name ="h_m") val meters: Double?,
        @ColumnInfo(name ="h_f") val feet: Double?
)

data class ReturnPayloadVol(
        @ColumnInfo(name ="rp_m") val cubicMeters: Double?,
        @ColumnInfo(name ="rp_f") val cubicFeet: Double?
)

data class ReturnPayloadMass(
        @ColumnInfo(name ="rp_kg") val kg: Double?,
        @ColumnInfo(name ="rp_lb") val lb: Double?
)

data class Trunk(
        @Embedded val trunkVolume: TrunkVolume?,
        @Embedded val cargo: Cargo?
)

data class Cargo(
        val solarArray: Double?,
        val unpressurizedCargo: Boolean?
)

data class TrunkVolume(
        @ColumnInfo(name ="t_m") val cubicMeters: Double?,
        @ColumnInfo(name ="t_f") val cubicFeet: Double?
)

data class Diameter(
        @ColumnInfo(name ="d_m") val meters: Double?,
        @ColumnInfo(name ="d_f") val feet: Double?
)
