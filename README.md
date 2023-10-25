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
The program is built with Core Java Libaries, no external downloads will be required outside of the files included on this GitHub location. Errors with GameServer.java and SupabaseOperations.java will not effect the functionality for Sprint 3.
The current program version has been successfully tested on Mac OS OpenJDK 17.0.5 and Windows 10 java 19.0.2
Within Visual Studio Code, select the Game.java file and use the run Java button in the upper right corner to run the program.

## Working Stage - Sprint 3
The Play Action Screen is implemented. At its current state, test events have been implemented to ensure that the game feed and player scores are functioning properly within the screen. Within the Play Action Screen, using the "A" key will result in every player earning 10 points. These points will be added to display each team's total score. Along with adding points, an event messages equal to the number of players will be printed. If there are fours players then four messages will be printed. At the moment, the message will be the first codename on the red team hitting the first codename on the green team. Each event is numbered and the events will disapear once they reach the bottom edge of the Event Window. The size of the Player Window and Events Window within the Play Action Screen are dynamic and will change based on the number of players on each team. Using the "Space" key at the Player Action Screen will cause the active game to end. When the game ends, all events and player scores will be cleared. Codename, ID and Equitment information will remain from the previous game and can be reused for another game instance. Pressing the 'Z' key at the Player Roster Screen will clear all player entries and reset the teams.  

Additional functions added: A count down will now display after pressing 'CTRL' to start the game from the Player Roster Screen, it will then display a 30 second timer before switching to the Play Action Screen with the listed players on both teams, their scores, and a game feed that will show the interactions between players (e.g. who got hit by who and if player has tagged the other teams base.). 

The program can be successfully completed without the use of a mouse. The "Escape" key will close the main window when the user is not actively entering player information and is on the player roster screen. User controls have been provided for each phase of the program with the general controls located either at the top or bottom of the screen.

The font of the program will depend on the fonts present on your computer. The "Georgia" typeface is the ground truth font for the Player Roster Screen which is present on Mac OS. Windows devices without the "Georgia" font could have text that goes over the bounds of the player entry boxes. The rest of the program uses "Times New Roman" as the font.

The file base also includes the implementation of a UDP sockets.

## Contributing

Pull/Push requests are welcome for main team members only. For major changes, please consult all team members and come to a consensus before commiting.

Please make sure to test before pushing changes to the repository to ensure integrity of project.
