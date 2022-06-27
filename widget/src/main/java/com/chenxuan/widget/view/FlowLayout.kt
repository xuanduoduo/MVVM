package com.chenxuan.widget.view

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * @author cx
 */
class FlowLayout @JvmOverloads constructor(
    context: Context?,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ViewGroup(context, attributeSet, defStyleAttr, defStyleRes) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        measureChildren(widthMeasureSpec, heightMeasureSpec)

        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        val widthSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSize = MeasureSpec.getSize(heightMeasureSpec)
        val height = realLayout(widthSize)
        val realHeight = if (heightMode == MeasureSpec.EXACTLY) heightSize else height

        setMeasuredDimension(widthSize, realHeight)
    }

    private fun realLayout(width: Int): Int {
        var childWidth: Int
        var childHeight: Int
        var layoutWidth = 0
        var layoutHeight = 0
        var lineHeight = 0
        var left: Int
        var top: Int
        var right: Int
        var bottom: Int

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val lp = child.layoutParams as MarginLayoutParams
            childWidth = child.measuredWidth
            childHeight = child.measuredHeight
            if (layoutWidth < width && (width - layoutWidth) > childWidth) {//不换行
                left = layoutWidth + lp.leftMargin
                top = layoutHeight + lp.topMargin
                right = left + childWidth
                bottom = top + childHeight
            } else {//换行
                layoutHeight += lineHeight
                layoutWidth = 0
                lineHeight = 0
                left = layoutWidth + lp.leftMargin
                top = layoutHeight + lp.topMargin
                right = left + childWidth
                bottom = top + childHeight
            }
            layoutWidth += (childWidth + lp.leftMargin + lp.rightMargin)
            val realLineHeight = childHeight + lp.topMargin + lp.bottomMargin
            lineHeight = if (realLineHeight > lineHeight) realLineHeight else lineHeight
            child.layout(left, top, right, bottom)
        }
        return layoutHeight + lineHeight
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {

    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return MarginLayoutParams(context, attrs)
    }
}