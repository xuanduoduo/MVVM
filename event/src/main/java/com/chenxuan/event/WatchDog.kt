package com.chenxuan.event

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.Executors

object WatchDog : LifecycleEventObserver {
    private val events: MutableMap<Any, List<WatchMethod>> = mutableMapOf()
    private val handler = Handler(Looper.getMainLooper())
    private val executors = Executors.newCachedThreadPool()

    fun watch(target: Any) {
        val watchMethods = events[target]
        if (watchMethods == null) {
            val methods = getWatchMethods(target)
            events[target] = methods
        }
        if (target is LifecycleOwner) handleLifecycle(target)
    }

    fun unWatch(target: Any) {
        val watchMethods = events[target]
        watchMethods?.run { events.remove(target) }
    }

    fun post(event: Any) {
        events.forEach {
            it.value.forEach { watchMethod ->
                if (watchMethod.event.isAssignableFrom(event::class.java)) {
                    callMethod(watchMethod, it.key, event)
                }
            }
        }
    }

    private fun callMethod(watchMethod: WatchMethod, target: Any, event: Any) {
        when (watchMethod.model) {
            WatchModel.NORMAL -> {
                realCall(watchMethod, target, event)
            }
            WatchModel.MAIN -> {
                handler.post {
                    realCall(watchMethod, target, event)
                }
            }
            WatchModel.ASYNC -> {
                executors.execute {
                    realCall(watchMethod, target, event)
                }
            }
        }
    }

    private fun realCall(watchMethod: WatchMethod, target: Any, event: Any) {
        watchMethod.method.invoke(target, event)
    }

    private fun getWatchMethods(target: Any): List<WatchMethod> {
        val result = mutableListOf<WatchMethod>()
        val clazz = target.javaClass
        clazz.declaredMethods.forEach {
            it ?: return@forEach
            val annotation = it.getAnnotation(Watch::class.java) ?: return@forEach
            val model = annotation.model
            val params = it.parameterTypes
            if (params.size != 1) throw Exception("Event can only have one param")
            if (params[0].interfaces.contains(WatchBean::class.java)) {
                result.add(WatchMethod(it, model, params[0]))
            }
        }
        return result
    }

    private fun handleLifecycle(owner: LifecycleOwner) {
        owner.lifecycle.addObserver(this)
    }

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {
        if (event == Lifecycle.Event.ON_DESTROY) unWatch(source)
    }
}