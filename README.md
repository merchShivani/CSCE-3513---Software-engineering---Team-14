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
The program is mostly built with core Java Libaries, all external libaries have been included in this repo.

The current program version has been successfully tested on Mac OS OpenJDK 17.0.5 and Windows 10 java 19.0.2

## How to Run?
Download this repo with the Download Zip command on Github. Unzip the files and place the folder on your computer, desktop is a good location to quickly grab the folder.
Within Visual Studio Code, open the folder for this repo use the run Java button in the upper right corner to run the program. When prompted select Game as the file to run.

All button prompts should be present on the Photon game User Interface. At the conclusion of the gameplay timer, a message will apear at the top prompting the user to use the space key to end the game and return to the player selection screen.

## Used Libraries
In the event of compiling issues. Make sure the following libraries are refrenced in the build path of the project. The files have been provided in this repo.
"lib/**/*.jar",
"json-20230618.jar",
"jl1.0.1.jar"

## Testing Clients
There are two methods for testing the UDP connection for the main program.

Option 1 - Python Traffic Generator
Within this Github Repo is the Python traffic generator provided by our instructor. To run the generator, open a new Visual Studio Code window and open only the python_trafficgenarator_v2.py file. Compile and run the file within the new window. Enter two players on each team (Red must be Odd Equipment ID and Green must be Even Equipment ID) on the main program then add the four players to the traffic generator. The generator will wait once the players are entered. Return to the main file and continue to the game counter. Once gameplay starts the generator will produce events for all four players until the gameplay timer hits zero.

Option 2 - Client File (Test Base Hit and Friendly Fire)
Within this Github Repo is a Java file named Client.java, this file can be ran independent of the main Game file and can be used to test the Photon game including base hit, friendly fire as well as normal scoring. To run the Client, have the main program running at the player add screen, open a new Visual Studio Code window and open only the Client.java file. Compile and run the file. With the key board follow the prompts and enter the Equipment ID for the player hitting and the player being hit in the following format 1:2 . In the event of two Equipment ID numbers on the same team, the event will call friendly fire and return the equipment id that did the friendly fire. If a player hits the other team's base a stylized "B" will appear next to the player's name until the end of the game.

## Sprint 4
Music
Now, each gameplay session is accompanied by a soundtrack. Players can expect a diverse and engaging auditory experience as they navigate through the laser tag arena.

Stylized "B" for Base Hits
To provide instant feedback and enhance the visual cues during gameplay, we've added a stylized "B" that appears whenever a player successfully hits the base. This visual indicator not only acknowledges the player's achievement but also makes every interaction with the base.

Game Execution with Traffic Generator
As part of our commitment to testing and performance optimization, we've integrated the game with a traffic generator provided by our instructor. This tool allows us to simulate varying levels of network activity, helping us assess the game's robustness and performance under different conditions. By running the game with the traffic generator, we aim to identify and address potential network-related challenges, ensuring a seamless and enjoyable multiplayer gaming experience.

## Contributing

Pull/Push requests are welcome for main team members only. For major changes, please consult all team members and come to a consensus before commiting.

Please make sure to test before pushing changes to the repository to ensure integrity of project.
