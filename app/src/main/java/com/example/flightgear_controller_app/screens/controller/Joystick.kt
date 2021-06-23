package com.example.flightgear_controller_app.screens.controller


import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import kotlin.math.pow
import kotlin.math.sqrt

/**
 *  Joystick custom view override view and implement OnTouchListener
 *   has big circle as bg and inside it small circle as joystick button(button)
 * @param paint painter of big circle(background)
 * @param strokePaint painter of strokes
 * @param circlePaint painter of small circle aka joystick's button
 * @param big_x big circle x value
 * @param big_y big circle y value
 * @param big_radius big circle radius
 * @param small_radius small circle radius
 * @param small_x small circle x value
 * @param small_y small circle y value
 * @param moveX x value of movement
 * @param moveY y value of movement
 * @param service onMoveListener notifier
 */
class Joystick : View, OnTouchListener {
    private val paint = Paint()
    private val strokePaint = Paint()
    private val circlePaint = Paint()
    private var big_x = 0f
    private var big_y = 0f
    private var big_radius = 0f
    private var small_radius = 0f
    private var small_x = 0f
    private var small_y = 0f
    private var moveX = 0f
    private var moveY = 0f
    lateinit var service : OnMoveListener

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }


    /**
     * init the position of joystick to center of screen
     */
    private fun initPosition() {
        big_x = (width / 2).toFloat()
        small_x = big_x
        moveX = small_x
        big_y = (height / 2).toFloat()
        small_y = big_y
        moveY = small_y

    }

    /**
     * init members of joystick , called in every constructor
     */
    private fun init() {
        setOnTouchListener(this)
        initPosition()
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
        paint.color = Color.GRAY
        strokePaint.isAntiAlias = true
        strokePaint.style = Paint.Style.STROKE
        strokePaint.color = Color.BLACK
        strokePaint.strokeWidth = 10f
        circlePaint.isAntiAlias = true
        circlePaint.style = Paint.Style.FILL
        circlePaint.color = Color.RED
    }

    /**
     * Draw the joystick
     * @param canvas the canvas joystick draw on
     */
    override fun onDraw(canvas: Canvas) {
        drawBigCircle(canvas)
        drawSmallCircle(canvas)
    }

    /**
     * draw big circle
     * @param canvas the canvas big circle draw on
     */
    private fun drawBigCircle(canvas: Canvas) {
        canvas.drawCircle(big_x, big_y, big_radius, paint)
        canvas.drawCircle(big_x, big_y, big_radius, strokePaint)
    }
    /**
     * draw small circle
     * @param canvas the canvas small circle draw on
     */
    private fun drawSmallCircle(canvas: Canvas) {
        val x = small_x + moveX - big_x
        val y = small_y + moveY - big_y
        if(::service.isInitialized) {
            val a = (moveX- big_x) / big_radius
            val e = -((moveY - big_y)/big_radius)
            service.onChange(a.toDouble(),e.toDouble())
        }
        canvas.drawCircle(
            x,
            y,
            small_radius,
            circlePaint
        )
        canvas.drawCircle(
            x,
            y,
            small_radius,
            strokePaint
        )
    }

    /**
     * called when screen size is changed ,
     * init joystick position to the center of new screen
     */
    override fun onSizeChanged(w: Int, h: Int, oldW: Int, oldH: Int) {
        super.onSizeChanged(w, h, oldW, oldH)
        initPosition()
        big_radius = (w.toFloat() /  2 )
        big_radius -= (big_radius / 2.5.toFloat())
        small_radius = big_radius /2
    }

    /**
     * Move the button (small circle) of joystick when user touches the screen
     */
    override fun onTouch(v: View, event: MotionEvent): Boolean {

        if (v == this) {
            if (event.action != MotionEvent.ACTION_UP) {
                val newX = event.x
                val newY = event.y
                val displacement = sqrt(
                    (newX - big_x).toDouble().pow(2.0) +
                            (newY - big_y).toDouble().pow(2.0)
                ).toFloat()
                // check if need to constraint the movement
                if (displacement < big_radius) {
                    moveX = newX
                    moveY = newY
                } else {
                    // constrait the movement
                    moveX = (newX - big_x) * big_radius / displacement + big_x
                    moveY = (newY - big_y) * big_radius / displacement + big_y
                }
            } else {
                // if user leaves the touch circle is going back to middle
                moveX = big_x
                moveY = big_y
            }
            invalidate()
        }
        return true
    }

    override fun performClick(): Boolean {
        super.performClick()
        return true
    }

}
