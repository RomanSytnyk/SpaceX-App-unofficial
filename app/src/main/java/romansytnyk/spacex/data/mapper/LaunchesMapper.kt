package romansytnyk.spacex.data.mapper

import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.data.db.entity.*

object LaunchesMapper {
    fun mapDtoToLaunchEntity(launch: Launch, isPastLaunches: Boolean = false): LaunchEntity {
        return LaunchEntity(flightNumber = launch.flightNumber,
                launchYear = launch.launchYear,
                launchDateUnix = launch.launchDateUnix,
                launchDateUtc = launch.launchDateUtc,
                launchDateLocal = launch.launchDateLocal,
                rocket = LaunchRocket(launch.rocket?.rocketId,
                        launch.rocket?.rocketName,
                        launch.rocket?.rocketType,
                        LaunchFirstStage(launch.rocket?.firstStage?.cores
                                ?.map { Core(it?.coreSerial, it?.flight, it?.reused, it?.landSuccess) }),
                        LaunchSecondStage(launch.rocket?.secondStage?.payloads
                                ?.map { Payload(it?.payloadId, it?.reused, it?.payloadType, it?.payloadMassKg, it?.payloadMassLbs, it?.orbit, it?.customers) })),
                reuse = Reuse(launch.reuse?.core,
                        launch.reuse?.sideCore1,
                        launch.reuse?.sideCore2,
                        launch.reuse?.fairings,
                        launch.reuse?.capsule),
                launchSite = LaunchSite(launch.launchSite?.siteId,
                        launch.launchSite?.siteName,
                        launch.launchSite?.siteNameLong),
                launchSuccess = launch.launchSuccess,
                links = Links(launch.links?.missionPatch,
                        launch.links?.articleLink,
                        launch.links?.videoLink,
                        launch.links?.redditLink,
                        launch.links?.youTubeId),
                details = launch.details,
                missionName = launch.missionName,
                missionId = launch.missionId?.joinToString(),
                isPastLaunch = isPastLaunches
        )
    }
}