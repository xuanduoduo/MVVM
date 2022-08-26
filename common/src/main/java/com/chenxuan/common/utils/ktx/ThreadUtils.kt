package com.chenxuan.common.utils.ktx

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

/**
 * @author cx
 */
private val handler = Handler(Looper.getMainLooper())
private val coreSize = Runtime.getRuntime().availableProcessors() + 1

private val fix: ExecutorService = Executors.newFixedThreadPool(coreSize)
private val cache: ExecutorService = Executors.newCachedThreadPool()
private val single: ExecutorService = Executors.newSingleThreadExecutor()
private val scheduled: ScheduledExecutorService = Executors.newScheduledThreadPool(coreSize)

fun <T> T.mainThread(delayMillis: Long = 0, block: T.() -> Unit) {
    handler.postDelayed({
        block()
    }, delayMillis)
}

fun <T> T.singlePool(block: T.() -> Unit) {
    single.execute {
        block()
    }
}

fun <T> T.fixPool(block: T.() -> Unit) {
    fix.execute {
        block()
    }
}

fun <T> T.cachePool(block: T.() -> Unit) {
    cache.execute {
        block()
    }
}