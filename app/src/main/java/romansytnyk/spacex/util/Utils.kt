package romansytnyk.spacex.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Roman on 03.03.2018
 */
object Utils {
    fun isOnline(context: Context?): Boolean {
        val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val nw = connectivityManager.activeNetwork ?: return false
            val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
            return when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                //for other device how are able to connect with Ethernet
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            val nwInfo = connectivityManager.activeNetworkInfo ?: return false
            return nwInfo.isConnected
        }
    }
}

fun String.formatLaunchDateToUserTimezone(): String {
    var convertedDate = ""
    try {
        val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault())
        utcFormat.timeZone = TimeZone.getTimeZone("UTC")
        val utcDate = utcFormat.parse(this)

        val currentFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        currentFormat.timeZone = TimeZone.getTimeZone(Calendar.getInstance().timeZone.id)

        val getTimeZoneShort = SimpleDateFormat("z", Locale.US)
        val timeZoneShort = getTimeZoneShort.format(Calendar.getInstance().time)

        utcDate?.let {
            convertedDate = "${currentFormat.format(it)} ($timeZoneShort)"
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return convertedDate
}

fun String.formatLaunchDateToUTC(): String {
    var convertedDate = ""
    try {
        val utcFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        val utcDate = utcFormat.parse(this)

        val currentFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

        utcDate?.let {
            convertedDate = currentFormat.format(utcDate) + " (GMT)"
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return convertedDate
}

fun String.formatLaunchDateToNativeTimezone(): String {
    var convertedDate = ""
    try {
        val oldFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
        val oldFormatDate = oldFormat.parse(this)

        val currentFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        // Android can't parse this timezone
        var timeZone: String = this.substring(this.lastIndexOf("-"), this.length)
        if (this.contains("+")) {
            timeZone = this.substring(this.lastIndexOf("+"), this.length)
        }

        oldFormatDate?.let {
            convertedDate = "${currentFormat.format(oldFormatDate)} (GMT$timeZone)"
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

    return convertedDate
}