package com.example.onlinestore.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.example.onlinestore.R

class ObliqueStrikeTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : androidx.appcompat.widget.AppCompatTextView(context, attrs, defStyle) {

    private var dividerColor: Int = 0
    private var paint: Paint

    init {
        dividerColor = ContextCompat.getColor(context, R.color.grey)
        paint = Paint().apply {
            color = dividerColor
            strokeWidth = resources.getDimension(R.dimen.vertical_divider_width)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val startY = height * 0.8f
        val stopY = height - startY
        canvas.drawLine(0.0f, startY, width.toFloat(), stopY, paint)
    }
}