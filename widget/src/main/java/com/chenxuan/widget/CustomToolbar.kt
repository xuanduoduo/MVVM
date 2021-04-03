package com.chenxuan.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.chenxuan.widget.utils.Callback

/**
 * @author cx
 */
class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : RelativeLayout(context, attributeSet, defStyleAttr, defStyleRes) {
    private val view = LayoutInflater.from(context).inflate(R.layout.toolbar, this, true)
    private var back: ImageView = view.findViewById(R.id.toolbarBack)
    private var title: TextView = view.findViewById(R.id.toolbarContent)
    private var feature: TextView = view.findViewById(R.id.toolbarFeature)
    private var icon: ImageView = view.findViewById(R.id.toolbarIcon)
    private var separate: View = view.findViewById(R.id.toolbarSeparate)
    private var backListener: Callback? = null
    private var featureListener: Callback? = null
    private var iconListener: Callback? = null

    init {

        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CustomToolbar)
        val content = typedArray.getString(R.styleable.CustomToolbar_barContent)
        val contentColor = typedArray.getColor(
            R.styleable.CustomToolbar_barContentColor,
            context.resources.getColor(R.color.textBlack)
        )
        val featureText = typedArray.getString(R.styleable.CustomToolbar_featureText)
        val featureTextColor = typedArray.getColor(
            R.styleable.CustomToolbar_featureTextColor,
            context.resources.getColor(R.color.blue_266EFF)
        )
        val iconVisible = typedArray.getBoolean(R.styleable.CustomToolbar_barIcon, false)
        val separateVisible = typedArray.getBoolean(R.styleable.CustomToolbar_barSeparate, true)

        typedArray.recycle()

        title.text = content
        title.setTextColor(contentColor)
        feature.text = featureText
        feature.setTextColor(featureTextColor)
        icon.visibility = if (iconVisible) View.VISIBLE else View.GONE
        separate.visibility = if (separateVisible) View.VISIBLE else View.GONE

        back.setOnClickListener {
            if (backListener == null) {
                (context as Activity).finish()
            } else {
                backListener?.invoke()
            }
        }
        feature.setOnClickListener {
            featureListener?.invoke()
        }
        icon.setOnClickListener {
            iconListener?.invoke()
        }
    }

    fun backListener(callback: Callback): CustomToolbar {
        backListener = callback
        return this
    }

    fun featureListener(callback: Callback): CustomToolbar {
        featureListener = callback
        return this
    }

    fun iconListener(callback: Callback): CustomToolbar {
        iconListener = callback
        return this
    }

    fun setContent(content: String): CustomToolbar {
        title.text = content
        return this
    }

    fun setContentColor(color: Int): CustomToolbar {
        title.setTextColor(color)
        return this
    }

    fun setFeatureText(text: String): CustomToolbar {
        feature.text = text
        return this
    }

    fun setFeatureTextColor(color: Int): CustomToolbar {
        feature.setTextColor(color)
        return this
    }

    fun setIconVisible(boolean: Boolean): CustomToolbar {
        icon.visibility = if (boolean) View.VISIBLE else View.GONE
        return this
    }

    fun setSeparateVisible(boolean: Boolean): CustomToolbar {
        separate.visibility = if (boolean) View.VISIBLE else View.GONE
        return this
    }
}