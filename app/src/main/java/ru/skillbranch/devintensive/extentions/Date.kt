package ru.skillbranch.devintensive.extentions

import android.util.TimeUtils
import java.text.SimpleDateFormat
import java.util.*
import kotlin.IllegalArgumentException

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time;
    return this
}

fun Date.humanizeDiff(date: Date = Date()): String {
    val difference = date.time - this.time
    if (difference < 0) throw IllegalArgumentException("Дата не валидна")
    return when {
        difference < SECOND -> "только что"
        difference in SECOND..SECOND * 45 -> "несколько секунд назад"
        difference in SECOND * 45..SECOND * 75 -> "минуту назад"
        difference in SECOND * 75..MINUTE * 45 -> "${TimeUnits.MINUTE.plural((Math.abs(difference) / MINUTE).toInt())} назад"
        difference in MINUTE * 45..MINUTE * 75 -> "час назад"
        difference in MINUTE * 75..HOUR * 22 -> "${TimeUnits.HOUR.plural((Math.abs(difference) / HOUR).toInt())} назад"
        difference in 22 * HOUR..HOUR * 26 -> "день назад"
        difference in 26 * HOUR..DAY * 360 -> "${TimeUnits.DAY.plural((Math.abs(difference) / DAY).toInt())} назад"
        else -> "более года назад"
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        val enumList: Map<TimeUnits, Array<String>> = mapOf(
            SECOND to arrayOf("секунду", "секунды", "секунд"),
            MINUTE to arrayOf("минуту", "минуты", "минут"),
            HOUR to arrayOf("час", "часа", "часов"),
            DAY to arrayOf("день", "дня", "дней")
        )
        val preLastDigit = value % 100 / 10
        if (preLastDigit == 1) return "$value ${enumList.get(this)?.get(2)}"

        return when (value % 10) {
            1 -> "$value ${enumList.get(this)?.get(0)}"
            2, 3, 4 -> "$value ${enumList.get(this)?.get(1)}"
            else -> "$value ${enumList.get(this)?.get(2)}"
        }
    }

}