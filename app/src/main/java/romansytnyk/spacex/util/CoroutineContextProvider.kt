package romansytnyk.spacex.util

import kotlinx.coroutines.experimental.CommonPool
import kotlin.coroutines.experimental.CoroutineContext

/**
 * Created by Roman on 18.02.2018
 */
open class CoroutineContextProvider {
    open val UI: CoroutineContext by lazy { kotlinx.coroutines.experimental.android.UI }
    open val IO: CoroutineContext by lazy { CommonPool }
}