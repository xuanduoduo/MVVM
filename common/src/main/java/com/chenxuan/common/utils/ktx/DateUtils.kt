package com.chenxuan.common.utils.ktx

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author cx
 */

enum class DateFormat {
    AGO,
    AFTER
}

val 前 = DateFormat.AGO
val 后 = DateFormat.AFTER

/**
 * 年月日
 */
val simpleDateFormat: SimpleDateFormat
    get() = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

/**
 * day
 */
infix fun Int.天(dateFormat: DateFormat): String {
    return when (dateFormat) {
        DateFormat.AGO -> {
            Calendar.getInstance().run {
                add(Calendar.DAY_OF_YEAR, -this@天)
                simpleDateFormat.format(timeInMillis)
            }
        }
        DateFormat.AFTER -> {
            Calendar.getInstance().run {
                add(Calendar.DAY_OF_YEAR, +this@天)
                simpleDateFormat.format(timeInMillis)
            }
        }
    }
}

/**
 * week
 */
infix fun Int.周(dateFormat: DateFormat): String {
    return when (dateFormat) {
        DateFormat.AGO -> {
            Calendar.getInstance().run {
                add(Calendar.WEEK_OF_YEAR, -this@周)
                simpleDateFormat.format(timeInMillis)
            }
        }
        DateFormat.AFTER -> {
            Calendar.getInstance().run {
                add(Calendar.WEEK_OF_YEAR, +this@周)
                simpleDateFormat.format(timeInMillis)
            }
        }
    }
}

/**
 * month
 */
infix fun Int.月(dateFormat: DateFormat): String {
    return when (dateFormat) {
        DateFormat.AGO -> {
            Calendar.getInstance().run {
                add(Calendar.MONTH, -this@月)
                simpleDateFormat.format(timeInMillis)
            }
        }
        DateFormat.AFTER -> {
            Calendar.getInstance().run {
                add(Calendar.MONTH, +this@月)
                simpleDateFormat.format(timeInMillis)
            }
        }
    }
}

/**
 * year
 */
infix fun Int.年(dateFormat: DateFormat): String {
    return when (dateFormat) {
        DateFormat.AGO -> {
            Calendar.getInstance().run {
                add(Calendar.YEAR, -this@年)
                simpleDateFormat.format(timeInMillis)
            }
        }
        DateFormat.AFTER -> {
            Calendar.getInstance().run {
                add(Calendar.YEAR, +this@年)
                simpleDateFormat.format(timeInMillis)
            }
        }
    }
}

/**
 * 年月日时分秒
 */
val sdfDate: SimpleDateFormat
    get() = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())

/**
 * Date扩展属性，时间戳转为日期字符串
 */
val Date.formatDate: String
    get() = sdfDate.format(this.time)

/**
 * 年月日
 */
val sdf: SimpleDateFormat
    get() = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

/**
 * N天前
 */
val Int.daysAgo: String
    get() = Calendar.getInstance().run {
        this.add(Calendar.DAY_OF_YEAR, -this@daysAgo)
        sdf.format(this.timeInMillis)
    }

/**
 * N天后
 */
val Int.daysLater: String
    get() = Calendar.getInstance().run {
        this.add(Calendar.DAY_OF_YEAR, +this@daysLater)
        sdf.format(this.timeInMillis)
    }

/**
 * N周前
 */
val Int.weekAgo: String
    get() = Calendar.getInstance().run {
        this.add(Calendar.WEEK_OF_YEAR, -this@weekAgo)
        sdf.format(this.timeInMillis)
    }

/**
 * N周后
 */
val Int.weekLater: String
    get() = Calendar.getInstance().run {
        this.add(Calendar.WEEK_OF_YEAR, +this@weekLater)
        sdf.format(this.timeInMillis)
    }

/**
 * N月前
 */
val Int.monthAgo: String
    get() = Calendar.getInstance().run {
        this.add(Calendar.MONTH, -this@monthAgo)
        sdf.format(this.timeInMillis)
    }

/**
 * N月后
 */
val Int.monthLater: String
    get() = Calendar.getInstance().run {
        this.add(Calendar.MONTH, +this@monthLater)
        sdf.format(this.timeInMillis)
    }

/**
 * N年前
 */
val Int.yearAgo: String
    get() = Calendar.getInstance().run {
        this.add(Calendar.YEAR, -this@yearAgo)
        sdf.format(this.timeInMillis)
    }

/**
 * N年后
 */
val Int.yearLater: String
    get() = Calendar.getInstance().run {
        this.add(Calendar.YEAR, +this@yearLater)
        sdf.format(this.timeInMillis)
    }
