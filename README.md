# FlightGear-Controller-App





# Project Description

FlightGear-Controller is an **Android Application** written in Kotlin.
The app allows you to connect to FlightGear running simulator and control the aircraft
using a customize remote joystick.
The application uses the MVVM architecture . 
The model uses Active Object design pattern that in charge of sending data 
to the FlightGear server.

## Features

* Connect to FlightGear simulator via ***ip*** and ***port***
* Control the aircraft movement with customize Joystick
* Set the Throttle and Rudder of aircraft using sliders (seekbars)
* Disconnect button whenever you want to terminate the connection and end your flight
* Ending screen where you can decide if you want to go back and fly again.




# Requirements

* Flight simulator app for dekstop , which you can donwload from  [Link](https://www.flightgear.org/download/)
* Android Studio

##  Installation and Setup

After downloaded FlightGear Simulator ,in order to run the app please do the following steps:

1.   Run FG simulator
2.  Go to Setting - > Additional Settings  , and insert this settings :

> –--telnet=socket,in,10,127.0.0.1,6400,tcp

3.  Press Fly!
4. now FlightGear is all set and done waiting for you to connect.
you can change the view of the aircraft by presssing 'V' .
5. Download the source code (zip or git clone)



   


##  Demonstration Video


## UML diagram


