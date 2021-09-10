package com.chenxuan.gradle

class DefaultClassNameFilter : ClassNameFilter {

    override fun filter(className: String): Boolean {
        whiteList.forEach {
            if (className.startsWith(it)) {
                return true
            }
        }
        return false
    }

    companion object {
        val whiteList = mutableListOf<String>().apply {
            add("kotlin")
            add("org")
            add("androidx")
            add("android")
        }
    }
}