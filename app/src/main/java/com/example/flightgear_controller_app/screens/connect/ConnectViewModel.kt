package com.example.flightgear_controller_app.screens.connect

import androidx.lifecycle.ViewModel
import com.example.flightgear_controller_app.models.FGConnector


/**
 *  ConnectViewModel
 *      View model of the connect fragment
 *      @param flightGearIP  the ip of thr flight gear simulator the user want to connect to
 *      @param flightGearPort the port of the flight gear .......
 */
class ConnectViewModel : ViewModel() {
    // default values
    var flightGearIP : String = ""
    var flightGearPort : Int = 0

    /**
     * Opens the connection via Model
     */
    fun connectToFG() {
        val isConnected = FGConnector.connect(this.flightGearIP, this.flightGearPort)
        if (isConnected)
            FGConnector.running()
        else
            throw Exception()
    }
}