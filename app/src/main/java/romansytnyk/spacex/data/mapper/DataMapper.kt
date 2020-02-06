package romansytnyk.spacex.data.mapper

import romansytnyk.spacex.data.api.model.Capsule
import romansytnyk.spacex.data.api.model.Launch
import romansytnyk.spacex.data.api.model.Rocket
import romansytnyk.spacex.data.db.entity.*

object DataMapper {

    fun mapRocketToRocketEntity(rocket: Rocket): RocketEntity {
        return RocketEntity(name = rocket.name,
                type = rocket.type,
                active = rocket.active,
                stages = rocket.stages,
                boosters = rocket.boosters,
                costPerLaunch = rocket.costPerLaunch,
                successRatePct = rocket.successRatePct,
                firstFlight = rocket.firstFlight,
                company = rocket.company,
                country = rocket.country,
                description = rocket.description,
                height = Height(
                        rocket.height?.meters,
                        rocket.height?.feet
                ),
                diameter = Diameter(
                        rocket.diameter?.meters,
                        rocket.diameter?.feet
                ),
                mass = Mass(
                        rocket.mass?.kg,
                        rocket.mass?.lb
                ),
                firstStage = FirstStage(
                        rocket.firstStage?.reusable,
                        rocket.firstStage?.engines,
                        rocket.firstStage?.fuelAmountTons,
                        rocket.firstStage?.burnTimeSec
                ),
                secondStage = SecondStage(
                        rocket.secondStage?.engines,
                        rocket.secondStage?.fuelAmountTons,
                        rocket.secondStage?.burnTimeSec
                )
        )
    }

    fun mapCapsuleToCapsuleEntity(capsule: Capsule): CapsuleEntity {
        return CapsuleEntity(name = capsule.name,
                type = capsule.type,
                active = capsule.active,
                crewCapacity = capsule.crewCapacity,
                sidewallAngleDeg = capsule.sidewallAngleDeg,
                orbitDurationYr = capsule.orbitDurationYr,
                heatShield = HeatShield(
                        capsule.heatShield?.material,
                        capsule.heatShield?.sizeMeters,
                        capsule.heatShield?.tempDegrees,
                        capsule.heatShield?.devPartner
                ),
                launchPayloadMass = LaunchPayloadMass(
                        capsule.launchPayloadMass?.kg,
                        capsule.launchPayloadMass?.lb
                ),
                launchPayloadVol = LaunchPayloadVol(
                        capsule.launchPayloadVol?.cubicMeters,
                        capsule.launchPayloadVol?.cubicFeet),
                returnPayloadMass = ReturnPayloadMass(
                        capsule.returnPayloadMass?.kg,
                        capsule.returnPayloadMass?.lb
                ),
                returnPayloadVol = ReturnPayloadVol(
                        capsule.returnPayloadVol?.cubicMeters,
                        capsule.returnPayloadVol?.cubicFeet
                )
        )
    }

    fun mapLaunchToLaunchEntity(launch: Launch, isPastLaunches: Boolean = false): LaunchEntity {
        return LaunchEntity(flightNumber = launch.flightNumber,
                launchYear = launch.launchYear,
                launchDateUnix = launch.launchDateUnix,
                launchDateUtc = launch.launchDateUtc,
                launchDateLocal = launch.launchDateLocal,
                rocket = LaunchRocket(
                        launch.rocket?.rocketId,
                        launch.rocket?.rocketName,
                        launch.rocket?.rocketType,
                        LaunchFirstStage(
                                launch.rocket?.firstStage?.cores?.map {
                                    Core(it?.coreSerial,
                                            it?.flight,
                                            it?.reused,
                                            it?.landSuccess
                                    )
                                }),
                        LaunchSecondStage(
                                launch.rocket?.secondStage?.payloads?.map {
                                    Payload(it?.payloadId,
                                            it?.reused,
                                            it?.payloadType,
                                            it?.payloadMassKg,
                                            it?.payloadMassLbs,
                                            it?.orbit,
                                            it?.customers
                                    )
                                })),
                reuse = Reuse(
                        launch.reuse?.core,
                        launch.reuse?.sideCore1,
                        launch.reuse?.sideCore2,
                        launch.reuse?.fairings,
                        launch.reuse?.capsule
                ),
                launchSite = LaunchSite(
                        launch.launchSite?.siteId,
                        launch.launchSite?.siteName,
                        launch.launchSite?.siteNameLong
                ),
                launchSuccess = launch.launchSuccess,
                links = Links(
                        launch.links?.missionPatch,
                        launch.links?.articleLink,
                        launch.links?.videoLink,
                        launch.links?.redditLink,
                        launch.links?.youTubeId
                ),
                details = launch.details,
                missionName = launch.missionName,
                missionId = launch.missionId?.joinToString(),
                isPastLaunch = isPastLaunches
        )
    }
}