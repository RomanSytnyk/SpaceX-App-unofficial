package romansytnyk.spacex.data.db.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class LaunchEntity(
        @PrimaryKey
        var id: Int = 0,

        @ColumnInfo(name = "flight_number") val flightNumber: Int?,
        @ColumnInfo(name = "launch_year") val launchYear: String?,
        @ColumnInfo(name = "launch_date_unix") val launchDateUnix: Long?,
        @ColumnInfo(name = "launch_date_utc") val launchDateUtc: String?,
        @ColumnInfo(name = "launch_date_local") val launchDateLocal: String?,
        @Embedded val rocket: LaunchRocket?,
        @Embedded val reuse: Reuse?,
        @Embedded val launchSite: LaunchSite?,
        @ColumnInfo(name = "launch_success") val launchSuccess: Boolean?,
        @Embedded val links: Links?,
        @ColumnInfo(name = "details") val details: String?,
        @ColumnInfo(name = "mission_name") val missionName: String?,
        @ColumnInfo(name = "mission_id") val missionId: String?,
        @ColumnInfo(name = "isPastLaunch") val isPastLaunch: Boolean?
) : Parcelable

@Parcelize
data class Reuse(
        val core: Boolean?,
        val sideCore1: Boolean?,
        val sideCore2: Boolean?,
        val fairings: Boolean?,
        val capsule: Boolean?
) : Parcelable

@Parcelize
data class LaunchSite(
        val siteId: String?,
        val siteName: String?,
        val siteNameLong: String?
) : Parcelable

@Parcelize
data class Links(
        val missionPatch: String?,
        val articleLink: String?,
        val videoLink: String?,
        val redditLink: String?,
        val youTubeId: String?
) : Parcelable

@Parcelize
data class LaunchRocket(
        val rocketId: String?,
        val rocketName: String?,
        val rocketType: String?,
        @Embedded val firstStage: LaunchFirstStage?,
        @Embedded val secondStage: LaunchSecondStage?
) : Parcelable

@Parcelize
data class LaunchFirstStage(
        val cores: List<Core?>?
) : Parcelable

@Parcelize
data class Core(
        val coreSerial: String?,
        val flight: Int?,
        val reused: Boolean?,
        val landSuccess: Boolean?
) : Parcelable

@Parcelize
data class LaunchSecondStage(
        val payloads: List<Payload?>?
) : Parcelable

@Parcelize
data class Payload(
        val payloadId: String?,
        val reused: Boolean?,
        val payloadType: String?,
        val payloadMassKg: Double?,
        val payloadMassLbs: Double?,
        val orbit: String?,
        val customers: List<String?>?
) : Parcelable