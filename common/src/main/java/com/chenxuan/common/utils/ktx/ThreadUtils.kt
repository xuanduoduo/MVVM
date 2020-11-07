package com.chenxuan.common.utils.ktx

import android.os.Handler
import android.os.Looper
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @author cx
 */
private val handler = Handler(Looper.getMainLooper())
private val coreSize = Runtime.getRuntime().availableProcessors() + 1

private val fix: ExecutorService = Executors.newFixedThreadPool(coreSize)
private val cache: ExecutorService = Executors.newCachedThreadPool()
private val single: ExecutorService = Executors.newSingleThreadExecutor()
private val scheduled: ExecutorService = Executors.newScheduledThreadPool(coreSize)

/**
 * 切换到主线程
 */
fun <T> T.runOnUi(block: T.() -> Unit) {
    handler.post {
        block()
    }
}

/**
 * 延迟delayMillis后切换到主线程
 */
fun <T> T.runOnUiDelay(delayMillis: Long, block: T.() -> Unit) {
    handler.postDelayed({
        block()
    }, delayMillis)
}

/**
 * 子线程执行 SingleThreadPool
 */
fun <T> T.runOnSinglePool(block: T.() -> Unit) {
    single.execute {
        block()
    }
}

/**
 * 子线程执行 FixedThreadPool
 */
fun <T> T.rRunOnFixPool(block: T.() -> Unit) {
    fix.execute {
        block()
    }
}

/**
 * 子线程执行 CachedThreadPool
 */
fun <T> T.runOnCachePool(block: T.() -> Unit) {
    cache.execute {
        block()
    }
}