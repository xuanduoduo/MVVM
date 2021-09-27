package com.chenxuan.widget.dsl

import kotlin.properties.ObservableProperty
import kotlin.reflect.KProperty

/**
 * @author cx
 */

typealias OnChange<T> = (T) -> Unit

class State<T>(value: T, private val onChange: OnChange<T>) : ObservableProperty<T>(value) {

    override fun afterChange(property: KProperty<*>, oldValue: T, newValue: T) {
        super.afterChange(property, oldValue, newValue)
        onChange.invoke(newValue)
    }
}

class ObserverField<T>(initValue: T) {
    private var onChange: OnChange<T>? = null

    var value by State(initValue) {
        onChange?.invoke(it)
    }

    fun setOnChangeListener(onChange: OnChange<T>) {
        this.onChange = onChange
    }
}

fun <T> ObserverField<T>.setState(newValue: T) {
    value = newValue
}