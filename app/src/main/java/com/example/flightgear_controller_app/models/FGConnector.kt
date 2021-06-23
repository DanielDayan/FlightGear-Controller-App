package com.example.flightgear_controller_app.models

import android.util.Log
import java.io.PrintWriter
import java.net.Socket
import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingDeque
/**
- FGConnector Model -
Opens the connection the with FlightGear server , and sending data to the simulator
implemented as an Active Object with blocking queue

 */
object FGConnector {
    private lateinit var ip : String
    private var port : Int = 0
    private var socket : Socket? = null
    private var out : PrintWriter? = null
    private var queue : BlockingQueue<Runnable> = LinkedBlockingDeque()
    private var isRunning : Boolean = false

    /**
    Opens a socket (connection) with server
    @param Ip the server ip
    @param Port server port
    @return true if connection established , otherwise false (aka isRunning)
     */
    fun connect(Ip :String, Port: Int) : Boolean {
        this.ip = Ip
        this.port = Port
        val t = Thread {
            try {
                this.socket = Socket(ip, port)
            }
            catch (e : Exception) {
                Log.i("FGController","Can't connect to FlightGear at $ip")
                return@Thread
            }
            isRunning = true
            Log.i("FGController","Connected to FlightGear")
        }
        t.start()
        t.join()
        return isRunning
    }
    /**
    Open a Thread in background and waiting for task to be inserted into queue
     */
    fun running() {
        val t = Thread {
            out = PrintWriter(socket!!.getOutputStream(),true)
            while (isRunning) {
                //Log.i("FGController","Waiting...")
                try {
                    queue.take().run()
                }
                catch (Interrupted: Exception){}
            }
            Log.i("FGController","Finished, Disconnecting.")
            out!!.close()
            socket!!.close()
        }
        t.start()
    }
    /**
    Put writing to server task into queue

    @param line the line sent to server
     */
    fun sendLine(line : String) {
        queue.put(Runnable {
            this.out?.write(line)
            this.out?.flush()
        })
    }

    /**
     * disconnect from server
     *
     */
    fun disconnect() {
        queue.put(Runnable { this.isRunning = false })
    }
}