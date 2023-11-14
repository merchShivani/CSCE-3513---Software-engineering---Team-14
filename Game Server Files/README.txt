Three files are included for UDP.
-LaserTagPlayerClient: This is the client side file that will receive the transmission codes from the server.
-LaserTagGameServer: This is the server side file that the client will connect to and receive the transmitted codes.
-compileFiles:	Compiles files.	

Current State:
As of now the 2 files are able to communicate with each other. Once started the server will transmit the 202 code. This means the game has started. Some time after I have it set up for ending the game by transmitting code 221 three times. Once 221 is transmitted and received 3 times both the client and the server have a function that will end their loop and shut down the servers for both.

How to use:
Once you have both files downloaded, have them in the same folder for ease and open two terminals. One of these terminals will be the client and the other will be the server. Have compiled both files, for easier compilation use the 'compileFiles.bat' file. After compilation, launch the server by typing 'java LaserTagGameServer' in one terminal and have it running. In the other terminal run 'java LaserTagPlayerClient'. Once both are running in the terminal with the player client server you will see the received codes. In the server terminal you will see strings printed indicating the current stage of the game and server.

