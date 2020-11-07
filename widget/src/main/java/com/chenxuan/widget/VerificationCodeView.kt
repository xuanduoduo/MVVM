package com.chenxuan.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * @author cx
 */
class VerificationCodeView @JvmOverloads constructor(
    context: Context?,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : RelativeLayout(context, attributeSet, defStyleAttr, defStyleRes) {
    private var view =
        LayoutInflater.from(context).inflate(R.layout.view_verification_code, this, true)
    private var tv1: TextView
    private var tv2: TextView
    private var tv3: TextView
    private var tv4: TextView
    private var tv5: TextView
    private var tv6: TextView
    private var et: EditText

    val codes = arrayListOf<String>()

    var block: ((Boolean) -> Unit)? = null

    init {
        tv1 = view.findViewById(R.id.tvVerification1)
        tv2 = view.findViewById(R.id.tvVerification2)
        tv3 = view.findViewById(R.id.tvVerification3)
        tv4 = view.findViewById(R.id.tvVerification4)
        tv5 = view.findViewById(R.id.tvVerification5)
        tv6 = view.findViewById(R.id.tvVerification6)
        et = view.findViewById(R.id.etVerification)

        setTextViews()

        et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!s.isNullOrEmpty()) {
                    et.setText("")
                    if (codes.size < 6) {
                        codes.add(s.toString())
                        setTextViews()
                    }
                }
                block?.invoke((codes.size == 6))
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        })

        et.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && codes.size > 0) {
                codes.removeAt(codes.size - 1)
                setTextViews()
                block?.invoke((codes.size == 6))
                true
            } else {
                false
            }
        }
    }

    private fun setTextViews() {
        setDefaultViews()
        codes.size.let {
            if (it >= 1) {
                tv1.text = codes[0]
            }
            if (it >= 2) {
                tv2.text = codes[1]
            }
            if (it >= 3) {
                tv3.text = codes[2]
            }
            if (it >= 4) {
                tv4.text = codes[3]
            }
            if (it >= 5) {
                tv5.text = codes[4]
            }
            if (it == 6) {
                tv6.text = codes[5]
            }

            when (it) {
                0 -> tv1.setBackgroundResource(R.drawable.bg_text_checked)
                1 -> tv2.setBackgroundResource(R.drawable.bg_text_checked)
                2 -> tv3.setBackgroundResource(R.drawable.bg_text_checked)
                3 -> tv4.setBackgroundResource(R.drawable.bg_text_checked)
                4 -> tv5.setBackgroundResource(R.drawable.bg_text_checked)
                5 -> tv6.setBackgroundResource(R.drawable.bg_text_checked)
            }
        }
    }

    private fun setDefaultViews() {
        tv1.text = ""
        tv2.text = ""
        tv3.text = ""
        tv4.text = ""
        tv5.text = ""
        tv6.text = ""
        tv1.setBackgroundResource(R.drawable.bg_text_normal)
        tv2.setBackgroundResource(R.drawable.bg_text_normal)
        tv3.setBackgroundResource(R.drawable.bg_text_normal)
        tv4.setBackgroundResource(R.drawable.bg_text_normal)
        tv5.setBackgroundResource(R.drawable.bg_text_normal)
        tv6.setBackgroundResource(R.drawable.bg_text_normal)
    }

    fun getText(): String {
        return if (codes.size == 0) ""
        else {
            var result = ""
            for (code in codes) {
                result += code
            }
            result
        }
    }

    fun setListener(block: (Boolean) -> Unit) {
        this.block = block
    }
}