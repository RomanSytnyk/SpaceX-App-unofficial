package romansytnyk.spacex.data.api.util

/**
 * Base Class for handling errors/failures/exceptions.
 * Every feature specific failure should extend [FeatureFailure] class.
 */
sealed class Failure {
    class NetworkConnection: Failure()
    class ServerError(var message: String): Failure()

    /** * Extend this class for feature specific failures.*/
    abstract class FeatureFailure: Failure()
}