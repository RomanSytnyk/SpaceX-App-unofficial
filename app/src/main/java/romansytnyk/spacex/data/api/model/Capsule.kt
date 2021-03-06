package romansytnyk.spacex.data.api.model
import com.google.gson.annotations.SerializedName


/**
 * Created by Roman on 01.03.2018
 */

data class Capsule(
		@SerializedName("id") val id: String?,
		@SerializedName("name") val name: String?,
		@SerializedName("type") val type: String?,
		@SerializedName("active") val active: Boolean?,
		@SerializedName("crew_capacity") val crewCapacity: Int?,
		@SerializedName("sidewall_angle_deg") val sidewallAngleDeg: Int?,
		@SerializedName("orbit_duration_yr") val orbitDurationYr: Double?,
		@SerializedName("heat_shield") val heatShield: HeatShield?,
		@SerializedName("launch_payload_mass") val launchPayloadMass: LaunchPayloadMass?,
		@SerializedName("launch_payload_vol") val launchPayloadVol: LaunchPayloadVol?,
		@SerializedName("return_payload_mass") val returnPayloadMass: ReturnPayloadMass?,
		@SerializedName("return_payload_vol") val returnPayloadVol: ReturnPayloadVol?
)

data class LaunchPayloadMass(
		@SerializedName("kg") val kg: Double?,
		@SerializedName("lb") val lb: Double?
)

data class HeatShield(
		@SerializedName("material") val material: String?,
		@SerializedName("size_meters") val sizeMeters: Double?,
		@SerializedName("temp_degrees") val tempDegrees: Double?,
		@SerializedName("dev_partner") val devPartner: String?
)

data class LaunchPayloadVol(
		@SerializedName("cubic_meters") val cubicMeters: Double?,
		@SerializedName("cubic_feet") val cubicFeet: Double?
)

data class ReturnPayloadVol(
		@SerializedName("cubic_meters") val cubicMeters: Double?,
		@SerializedName("cubic_feet") val cubicFeet: Double?
)

data class ReturnPayloadMass(
		@SerializedName("kg") val kg: Double?,
		@SerializedName("lb") val lb: Double?
)