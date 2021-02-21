package com.chenxuan.gradle

interface ClassNameFilter {
    fun filter(className: String): Boolean
}