package ru.skillbranch.devintensive.extensions

import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits): Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val isFuture = time > date.time
    val diffDate = Math.abs(time - date.time)
    val daysDiff = diffDate / DAY
    val hourDiff = diffDate / HOUR
    val minuteDiff = diffDate / MINUTE
    val secondsDiff = diffDate / SECOND

    return when {
        daysDiff > 360 -> if (isFuture) "более чем через год" else "более года назад"
        hourDiff > 26 -> getPluralDay(daysDiff, isFuture)
        hourDiff in 23..26 -> if (isFuture) "через день" else "день назад"
        hourDiff <= 22 && minuteDiff > 75 -> getPluralHour(hourDiff, isFuture)
        minuteDiff in 46..75 -> if (isFuture) "через час" else "час назад"
        minuteDiff <= 45 && secondsDiff > 75 -> getPluralMinute(
            minuteDiff,
            isFuture
        )
        secondsDiff in 46..75 -> if (isFuture) "через минуту" else "минуту назад"
        secondsDiff in 2..45 -> if (isFuture) "через несколько секунд" else "несколько секунд назад"
        else -> "только что"
    }
}

private fun getPluralDay(day: Long, isFuture: Boolean): String {
    return when (getPluralType(day)) {
        Plural.ONE -> if (isFuture) "чере $day день" else "$day день назад"
        Plural.SOME -> if (isFuture) "через $day дня" else "$day дня назад"
        Plural.MANY -> if (isFuture) "через $day дней" else "$day дней назад"
    }
}

private fun getPluralHour(hour: Long, isFuture: Boolean): String {
    return when (getPluralType(hour)) {
        Plural.ONE -> if (isFuture) "чере $hour час" else "$hour час назад"
        Plural.SOME -> if (isFuture) "через $hour часа" else "$hour часа назад"
        Plural.MANY -> if (isFuture) "через $hour часов" else "$hour часов назад"
    }
}

private fun getPluralMinute(minute: Long, isFuture: Boolean): String {
    return when (getPluralType(minute)) {
        Plural.ONE -> if (isFuture) "чере $minute минуту" else "$minute минуту назад"
        Plural.SOME -> if (isFuture) "через $minute минуты" else "$minute минуты назад"
        Plural.MANY -> if (isFuture) "через $minute минут" else "$minute минут назад"
    }
}

private fun getSecondWord(num: Int): String {
    return when (getPluralType(num.toLong())) {
        Plural.ONE -> "секунду"
        Plural.SOME -> "секунды"
        Plural.MANY -> "секунд"
    }
}

private fun getMinuteWord(num: Int): String {
    return when (getPluralType(num.toLong())) {
        Plural.ONE -> "минуту"
        Plural.SOME -> "минуты"
        Plural.MANY -> "минут"
    }
}

private fun getHourWord(num: Int): String {
    return when (getPluralType(num.toLong())) {
        Plural.ONE -> "час"
        Plural.SOME -> "часа"
        Plural.MANY -> "часов"
    }
}

private fun getDayWord(num: Int): String {
    return when (getPluralType(num.toLong())) {
        Plural.ONE -> "день"
        Plural.SOME -> "дня"
        Plural.MANY -> "дней"
    }
}

private fun getPluralType(num: Long): Plural {
    var n = Math.abs(num).toInt()
    n %= 100
    if (n in 5..20) {
        return Plural.MANY
    }
    n %= 10
    if (n == 1) {
        return Plural.ONE
    }
    if (n in 2..4) {
        return Plural.SOME
    }
    return Plural.MANY
}

fun TimeUnits.plural(num: Int): String {
    return when (this) {
        TimeUnits.SECOND -> "$num ${getSecondWord(num)}"
        TimeUnits.MINUTE -> "$num ${getMinuteWord(num)}"
        TimeUnits.HOUR -> "$num ${getHourWord(num)}"
        TimeUnits.DAY -> "$num ${getDayWord(num)}"
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}

enum class Plural {
    ONE,
    MANY,
    SOME
}