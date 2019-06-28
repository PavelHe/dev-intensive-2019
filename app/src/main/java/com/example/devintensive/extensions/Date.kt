package com.example.devintensive.extensions

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
    val diffDate = date.time - time
    val isYearDif = diffDate / DAY > 360
    val isDaysDiff = diffDate / DAY > 0
    val isHourDiff = diffDate / HOUR > 0
    val isMinuteDiff = diffDate / MINUTE > 0
    val isSecondsDiff = diffDate / SECOND >= 0

    if (isYearDif)
        return getYearDiff()
    if (isDaysDiff)
        return getDaysDiff(diffDate / DAY)
    if (isHourDiff)
        return getHoursDiff(diffDate / HOUR)
    if (isMinuteDiff)
        return getMinutesDiff(diffDate / MINUTE)
    if (isSecondsDiff)
        return getSecondsDiff(diffDate / SECOND)

    return ""
}

private fun getYearDiff(): String {
    return "более года назад"
}

private fun getDaysDiff(diff: Long): String {
    return if (diff == 1L) "день назад" else "$diff дней назад"
}

private fun getHoursDiff(diff: Long): String {
    return if (diff in 22..26) "день назад" else "$diff часов назад"
}

private fun getMinutesDiff(diff: Long): String {
    return if (diff in 45..75) "час назад" else "$diff минут назад"
}

private fun getSecondsDiff(diff: Long): String {
    return when (diff) {
        in 0..1 -> "только что"
        in 1..45 -> "несколько секунд назад"
        in 45..75 -> "минуту назад"
        else -> "$diff секунда назад"
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}