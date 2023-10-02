# CSCE-3513---Software-engineering---Team-14
## Photon Laser Tage Software

Photon laser tag is the main software for the operation of a laser tag game. It handles players, player names, point systems, players hit registration and sensor functionality as well score board graphics/display. The goal of the software is to successfully have hardware work in parallel with the software to have a complete functioning laser tag game. 

## Team Members
```
-Adam Eisa = Eisa992
-Jarrett Hobbs = JarrettHobbs
-Shivani Merchant = merchShivani
-Christian Blunden = Blun3n
-Dhurv Mistry = DhrubMistry128
```

## Installation requirements
The current state of the project requires the use of [java](https://www.java.com/download/ie_manual.jsp). This will allow you to view and complie the code.
The program is built with Core Java Libaries, no external downloads will be required outside of the files included on this GitHub location.
The current program version has been successfully tested on Mac OS OpenJDK 17.0.5 and Windows 10 java 19.0.2

## Working Stage - Sprint 2
The GUI for the Photon Splash Screen and Player Entry Screen functionality are in a complete first draft form with the user able to add Player ID, Player Code Name and Player Equipment ID through a GUI window with no need for interaction with the terminal. New Player ID and Player Code Name will be printed on the screen with the Equipment ID being assigned to the new Player Object. Functions are in place to check if an ID is already in the instance of the game, however there is no functionality with a database at the moment.

The program can be successfully completed without the use of a mouse. The "Escape" key will close the main window when the user is not actively entering player information. User controls have been provided for each phase of the program with the general controls located at the bottom of the screen.

The font of the program will depend on the fonts present on your computer. The "Georgia" typeface is the ground truth font which is present on Mac OS. Windows devices without the "Georgia" font could have text that goes over the bounds of the player entry boxes. 

The file base also includes the implementation of a UDP sockets.

## Contributing

Pull/Push requests are welcome for main team members only. For major changes, please consult all team members and come to a consensus before commiting.

Please make sure to test before pushing changes to the repository to ensure integrity of project.
