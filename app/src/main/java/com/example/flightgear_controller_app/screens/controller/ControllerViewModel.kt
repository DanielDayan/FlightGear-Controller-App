package com.example.flightgear_controller_app.screens.controller

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.flightgear_controller_app.models.FGConnector

/**
* ViewModel containing all the logic needed to run the Controller
* @param normalizer  seekbar normalizer values
*/
class ControllerViewModel : ViewModel() {
    // rudder normalizer
    private val normalizer = 1000


    /**
     * send to the server new value of rudder
     * @param rud current rudder value from the rudderSeekBar
     * rudder values - [-1,1]
     */
    fun rudderChanged(rud : Int){
        val normalizedRud : Double = (rud - normalizer) / normalizer.toDouble()
        Log.i("DoubleCheck", "Check - $normalizedRud")
        val lineToConnector = "set /controls/flight/rudder $normalizedRud\r\n"
        FGConnector.sendLine(lineToConnector)
    }

    /**
     * *send to the server new value of throttle
     * @param throt current throtle value from the throttleSeekBar
     * throttle values - [ 0, 1]
     */

    fun throttleChanged(throt : Int){
        // change it throttle  values : [0,1]
        val normalizedThrot : Double = throt / (2 * normalizer).toDouble()
        val lineToConnector = "set /controls/engines/current-engine/throttle $normalizedThrot\r\n"
        FGConnector.sendLine(lineToConnector)
    }
    /**
     * *send to the server new value of aileron
     * @param aileron current aileron value from the joystick x Axis
     * aileron values - [ -1, 1]
     */
    fun aileronChanged(aileron: Double) {
        val lineToConnector = "set /controls/flight/aileron $aileron\r\n"
        FGConnector.sendLine(lineToConnector)
    }
    /**
     * *send to the server new value of elevator
     * @param elevator current elevator value from the joystick u Axis
     * elevator values - [ -1, 1]
     */
    fun elevatorChanged(elevator: Double) {
        val lineToConnector = "set /controls/flight/elevator $elevator\r\n"
        FGConnector.sendLine(lineToConnector)
    }

    /**
     * Callback called when the ViewModel is destroyed
     * disconnect from server
     */
    override fun onCleared() {
        super.onCleared()
        FGConnector.disconnect()
        Log.i("ControllerViewModel", "ControllerViewModel destroyed!")
    }

}
