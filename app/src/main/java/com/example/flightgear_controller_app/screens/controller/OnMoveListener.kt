package com.example.flightgear_controller_app.screens.controller

/**
 * OnMoveListener interface
 *  override onChange method in order to decide what happen when joystick moved
 */
interface OnMoveListener {
    // set aileron and elevator on movement
    fun onChange(a : Double, e : Double)
}