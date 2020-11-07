package com.chenxuan.common.utils.ktx

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/**
 * @author cx
 */
inline fun EditText.afterChangeListener(crossinline block: (s: Editable?) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            block(s)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }

    })
}

typealias FormChangeListener = () -> Unit

fun addFormCompleteListener(array: Array<EditText>, block: (boolean: Boolean) -> Unit) {
    val items = HashMap<EditText, Boolean>(array.size)

    val formChangeListener: FormChangeListener = form@{
        for (entry in items) {
            if (!entry.value) {
                block(false)
                return@form
            }
        }
        block(true)
    }

    for (editText in array) {
        items[editText] = false
        editText.afterChangeListener {
            items[editText] = !it.isNullOrEmpty()
            formChangeListener.invoke()
        }
    }
}