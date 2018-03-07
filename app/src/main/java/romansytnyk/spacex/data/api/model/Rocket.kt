package romansytnyk.spacex.data.api.model
import com.google.gson.annotations.SerializedName


/**
 * Created by Roman on 01.03.2018
 */


data class Rocket(
		@SerializedName("id") val id: String?,
		@SerializedName("name") val name: String?,
		@SerializedName("type") val type: String?,
		@SerializedName("active") val active: Boolean?,
		@SerializedName("stages") val stages: Int?,
		@SerializedName("boosters") val boosters: Int?,
		@SerializedName("cost_per_launch") val costPerLaunch: Int?,
		@SerializedName("success_rate_pct") val successRatePct: Int?,
		@SerializedName("first_flight") val firstFlight: String?,
		@SerializedName("country") val country: String?,
		@SerializedName("company") val company: String?,
		@SerializedName("height") val height: Height?,
		@SerializedName("diameter") val diameter: Diameter?,
		@SerializedName("mass") val mass: Mass?,
		@SerializedName("payload_weights") val payloadWeights: List<PayloadWeight?>?,
		@SerializedName("first_stage") val firstStage: FirstStage?,
		@SerializedName("second_stage") val secondStage: SecondStage?,
		@SerializedName("engines") val engines: Engines?,
		@SerializedName("landing_legs") val landingLegs: LandingLegs?,
		@SerializedName("description") val description: String?
)

data class Mass(
		@SerializedName("kg") val kg: Double?,
		@SerializedName("lb") val lb: Double?
)

data class Engines(
		@SerializedName("number") val number: Int?,
		@SerializedName("type") val type: String?,
		@SerializedName("version") val version: String?,
		@SerializedName("layout") val layout: String?,
		@SerializedName("engine_loss_max") val engineLossMax: Double?,
		@SerializedName("propellant_1") val propellant1: String?,
		@SerializedName("propellant_2") val propellant2: String?,
		@SerializedName("thrust_sea_level") val thrustSeaLevel: ThrustSeaLevel?,
		@SerializedName("thrust_vacuum") val thrustVacuum: ThrustVacuum?,
		@SerializedName("thrust_to_weight") val thrustToWeight: Double?
)

data class ThrustSeaLevel(
		@SerializedName("kN") val kN: Double?,
		@SerializedName("lbf") val lbf: Double?
)

data class ThrustVacuum(
		@SerializedName("kN") val kN: Double?,
		@SerializedName("lbf") val lbf: Double?
)

data class SecondStage(
		@SerializedName("engines") val engines: Int?,
		@SerializedName("fuel_amount_tons") val fuelAmountTons: Double?,
		@SerializedName("burn_time_sec") val burnTimeSec: Double?,
		@SerializedName("thrust") val thrust: Thrust?,
		@SerializedName("payloads") val payloads: Payloads?
)

data class Thrust(
		@SerializedName("kN") val kN: Double?,
		@SerializedName("lbf") val lbf: Double?
)

data class Payloads(
		@SerializedName("option_1") val option1: String?,
		@SerializedName("composite_fairing") val compositeFairing: CompositeFairing?
)

data class CompositeFairing(
		@SerializedName("height") val height: Height?,
		@SerializedName("diameter") val diameter: Diameter?
)

data class Diameter(
		@SerializedName("meters") val meters: Double?,
		@SerializedName("feet") val feet: Double?
)

data class Height(
		@SerializedName("meters") val meters: Double?,
		@SerializedName("feet") val feet: Double?
)

data class FirstStage(
		@SerializedName("reusable") val reusable: Boolean?,
		@SerializedName("engines") val engines: Int?,
		@SerializedName("fuel_amount_tons") val fuelAmountTons: Double?,
		@SerializedName("burn_time_sec") val burnTimeSec: Double?,
		@SerializedName("thrust_sea_level") val thrustSeaLevel: ThrustSeaLevel?,
		@SerializedName("thrust_vacuum") val thrustVacuum: ThrustVacuum?
)

data class PayloadWeight(
		@SerializedName("id") val id: String?,
		@SerializedName("name") val name: String?,
		@SerializedName("kg") val kg: Double?,
		@SerializedName("lb") val lb: Double?
)

data class LandingLegs(
		@SerializedName("number") val number: Int?
)