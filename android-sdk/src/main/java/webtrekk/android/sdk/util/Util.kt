package webtrekk.android.sdk.util

import android.content.Context
import android.os.Build
import androidx.room.Room
import androidx.room.RoomDatabase
import webtrekk.android.sdk.BuildConfig
import webtrekk.android.sdk.WebtrekkImpl
import java.util.*
import kotlin.random.Random

internal val currentOsVersion: String
    inline get() = Build.VERSION.RELEASE

internal val currentApiLevel: Int
    inline get() = Build.VERSION.SDK_INT

internal val currentDeviceManufacturer: String
    inline get() = Build.MANUFACTURER

internal val currentDeviceModel: String
    inline get() = Build.MODEL

internal val currentCountry: String
    inline get() = Locale.getDefault().country

internal val currentLanguage: String
    inline get() = Locale.getDefault().language

internal val currentTimeZone: Int
    inline get() = TimeZone.getDefault().rawOffset / 1000 / 60 / 60

internal val currentTimeStamp: Long
    inline get() = System.currentTimeMillis()

internal val currentWebtrekkVersion: String
    inline get() = BuildConfig.VERSION_NAME

internal val currentEverId: String
    inline get() = WebtrekkImpl.getInstance().sessions.getEverId()

internal val currentSession: String
    @Synchronized inline get() = WebtrekkImpl.getInstance().sessions.getCurrentSession()

internal val appFirstStart: String
    @Synchronized inline get() = WebtrekkImpl.getInstance().sessions.getAppFirstStart()

internal val trackDomain: String
    inline get() = WebtrekkImpl.getInstance().config.trackDomain

internal val trackIds: List<String>
    inline get() = WebtrekkImpl.getInstance().config.trackIds

internal fun generateEverId(): String {
    val date = currentTimeStamp / 1000
    val random = Random

    return "6${String.format("%010d%08d", date, random.nextLong(100000000))}"
}

internal fun <T : RoomDatabase> buildRoomDatabase(
    context: Context,
    databaseName: String,
    database: Class<T>
): T = Room.databaseBuilder(
    context.applicationContext,
    database,
    databaseName
).build()
