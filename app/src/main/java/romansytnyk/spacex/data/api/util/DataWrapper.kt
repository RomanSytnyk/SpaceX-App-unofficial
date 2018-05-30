package romansytnyk.spacex.data.api.util

data class DataWrapper<out T>(val data: T? = null,
                              val error: Failure? = null)