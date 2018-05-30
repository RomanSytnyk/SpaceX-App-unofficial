package romansytnyk.spacex.data.api.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LaunchesBunch(
		val futureLaunches: List<Launch>?,
		val pastLaunches: List<Launch>?
): Parcelable

@Parcelize
data class Launch(
		@SerializedName("flight_number") val flightNumber: Int?,
		@SerializedName("launch_year") val launchYear: String?,
		@SerializedName("launch_date_unix") val launchDateUnix: Long?,
		@SerializedName("launch_date_utc") val launchDateUtc: String?,
		@SerializedName("launch_date_local") val launchDateLocal: String?,
		@SerializedName("rocket") val rocket: LaunchRocket?,
		@SerializedName("reuse") val reuse: Reuse?,
		@SerializedName("launch_site") val launchSite: LaunchSite?,
		@SerializedName("launch_success") val launchSuccess: Boolean?,
		@SerializedName("links") val links: Links?,
		@SerializedName("details") val details: String?
) : Parcelable

@Parcelize
data class Reuse(
		@SerializedName("core") val core: Boolean?,
		@SerializedName("side_core1") val sideCore1: Boolean?,
		@SerializedName("side_core2") val sideCore2: Boolean?,
		@SerializedName("fairings") val fairings: Boolean?,
		@SerializedName("capsule") val capsule: Boolean?
) : Parcelable

@Parcelize
data class LaunchSite(
		@SerializedName("site_id") val siteId: String?,
		@SerializedName("site_name") val siteName: String?,
		@SerializedName("site_name_long") val siteNameLong: String?
) : Parcelable

@Parcelize
data class Links(
		@SerializedName("mission_patch") val missionPatch: String?,
		@SerializedName("article_link") val articleLink: String?,
		@SerializedName("video_link") val videoLink: String?,
		@SerializedName("reddit_campaign") val redditLink: String?
) : Parcelable

@Parcelize
data class LaunchRocket(
		@SerializedName("rocket_id") val rocketId: String?,
		@SerializedName("rocket_name") val rocketName: String?,
		@SerializedName("rocket_type") val rocketType: String?,
		@SerializedName("first_stage") val firstStage: LaunchFirstStage?,
		@SerializedName("second_stage") val secondStage: LaunchSecondStage?
) : Parcelable

@Parcelize
data class LaunchFirstStage(
		@SerializedName("cores") val cores: List<Core?>?
) : Parcelable

@Parcelize
data class Core(
		@SerializedName("core_serial") val coreSerial: String?,
		@SerializedName("flight") val flight: Int?,
		@SerializedName("reused") val reused: Boolean?,
		@SerializedName("land_success") val landSuccess: Boolean?
) : Parcelable

@Parcelize
data class LaunchSecondStage(
		@SerializedName("payloads") val payloads: List<Payload?>?
) : Parcelable

@Parcelize
data class Payload(
		@SerializedName("payload_id") val payloadId: String?,
		@SerializedName("reused") val reused: Boolean?,
		@SerializedName("customers") val customers: List<String?>?,
		@SerializedName("payload_type") val payloadType: String?,
		@SerializedName("payload_mass_kg") val payloadMassKg: Double?,
		@SerializedName("payload_mass_lbs") val payloadMassLbs: Double?,
		@SerializedName("orbit") val orbit: String?
) : Parcelable