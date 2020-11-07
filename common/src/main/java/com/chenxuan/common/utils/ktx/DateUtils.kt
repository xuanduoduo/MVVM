package com.chenxuan.common.utils.ktx

import java.text.SimpleDateFormat
import java.util.*

/**
 * @author cx
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