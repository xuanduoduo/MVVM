package com.chenxuan.event

import java.lang.reflect.Method

data class WatchMethod(val method: Method, val model: WatchModel, val event: Class<*>)