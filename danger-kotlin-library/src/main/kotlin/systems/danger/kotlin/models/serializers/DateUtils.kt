package systems.danger.kotlin.models.serializers

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressWarnings("unused")
internal fun fromISO8601UTC(dateStr: String): Date? {
    val tz = TimeZone.getTimeZone("UTC")
    val df = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    df.timeZone = tz

    val alternativeDf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")
    alternativeDf.timeZone = tz

    try {
        return df.parse(dateStr)
    } catch (e: ParseException) {
        try {
            return alternativeDf.parse(dateStr)
        } catch (e2: ParseException) {
            e.printStackTrace()
            e2.printStackTrace()
        }
    }

    return null
}
