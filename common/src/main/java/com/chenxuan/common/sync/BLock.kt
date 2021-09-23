package com.chenxuan.common.sync

import java.util.concurrent.locks.AbstractQueuedSynchronizer

class BLock {
    private val sync = Sync()

    fun lock() {
        sync.lock()
    }

    fun unlock() {
        sync.unlock()
    }

    private inner class Sync : AbstractQueuedSynchronizer() {
        fun lock() = acquire(1)

        override fun tryAcquire(arg: Int): Boolean {
            val currentThread = Thread.currentThread()
            val s = state
            if (s == 0) {
                if (compareAndSetState(0, arg)) {
                    exclusiveOwnerThread = currentThread
                    return true
                }
            } else if (currentThread === exclusiveOwnerThread) {
                val next = s + arg
                if (next < 0) throw Error("Maximum lock count exceeded")
                state = next
                return true
            }
            return false
        }

        fun unlock() {
            release(1)
        }

        override fun tryRelease(arg: Int): Boolean {
            val c: Int = state - arg
            if (Thread.currentThread() !== exclusiveOwnerThread) throw IllegalMonitorStateException()
            var free = false
            if (c == 0) {
                free = true
                exclusiveOwnerThread = null
            }
            state = c
            return free
        }
    }
}