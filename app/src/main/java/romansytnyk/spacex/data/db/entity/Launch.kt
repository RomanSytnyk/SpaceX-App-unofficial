package romansytnyk.spacex.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LaunchEntity(
        @PrimaryKey(autoGenerate = true)
        var id: Int,

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
        @ColumnInfo(name = "mission_id") val missionId: String?
)


data class Reuse(
        val core: Boolean?,
        val sideCore1: Boolean?,
        val sideCore2: Boolean?,
        val fairings: Boolean?,
        val capsule: Boolean?
)

data class LaunchSite(
        val siteId: String?,
        val siteName: String?,
        val siteNameLong: String?
)

data class Links(
        val missionPatch: String?,
        val articleLink: String?,
        val videoLink: String?,
        val redditLink: String?,
        val youTubeId: String?
)

data class LaunchRocket(
        val rocketId: String?,
        val rocketName: String?,
        val rocketType: String?
)