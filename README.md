#  FlightGear-Controller



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
2.  Go to Setting - > Additional Settings 


 ![image](https://user-images.githubusercontent.com/64739791/123251799-46363b00-d4f4-11eb-86db-dfc03d995769.png)

  and insert this settings :
  

> –--telnet=socket,in,10,127.0.0.1,6400,tcp

that should look like that :


![image](https://user-images.githubusercontent.com/64739791/123252020-7a116080-d4f4-11eb-88a9-64cbb165ff9d.png)


(or any other port you like)

3.  Press Fly!  
  
 ![image](https://user-images.githubusercontent.com/64739791/123252092-93b2a800-d4f4-11eb-9f6d-7b33a1a63034.png)


4. now FlightGear is all set and done waiting for you to connect.
you can change the view of the aircraft by presssing 'V' .
5. Download the source code (zip or git clone).
6. Open the source code with Android studio and run the app with android studio built in emulator
   or an android device you can plug in via usb to PC



# Operating Instructions

After FlightGear talent server is online and the app is running , 
the openning screen you will see is the ConnectorFragment where you need to insert IP address and Port
that you configure in setup stage.

![image](https://user-images.githubusercontent.com/64739791/123256578-cad78800-d4f9-11eb-8377-7a5a36aaf6a2.png)


click the connect button in order to connect to FG server.
While connected youll move to the ControllerFragment, thats the screen who conatins the joystick controlling the aircraft.

![image](https://user-images.githubusercontent.com/64739791/123254891-e2ae0c80-d4f7-11eb-8cf4-9af0d0280c65.png)



Look up to the setting bar in FlightGear and search for name of the current aircraft ur using, and click Autostart

![autostart](https://user-images.githubusercontent.com/64739791/123255877-fc9c1f00-d4f8-11eb-8e72-4ba86248093f.png)


that should be able to start aircraft's engines.

**!! NOTICE !!**

Keep Throttle Seekbar at 0 when pressing autostart


Move Throttle in order to start aircraft going, and navigate plane using Rudder and the Joystick

Pressing on **Disconnect** button at the end of screen will terminate connection with FlightGear server
and navigate user to the end screen : 

![image](https://user-images.githubusercontent.com/64739791/123255363-6a941680-d4f8-11eb-9805-662d4f98b2d5.png)

Pressing on **Fly Again** will get you back to start screen where you can start another flight.

Pressing on **Good Bye!** will close the app.


**Enjoy Your Flight**

##  Demonstration Video


## UML diagram


![FG_app_uml](https://user-images.githubusercontent.com/64739791/123252755-4daa1400-d4f5-11eb-975a-f0554d993132.jpeg)



