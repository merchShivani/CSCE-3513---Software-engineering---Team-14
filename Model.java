import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ConcurrentLinkedQueue;
import javazoom.jl.player.advanced.AdvancedPlayer;
import java.io.InputStream;
import java.net.DatagramSocket;
import java.net.SocketException;





class Model
{
	int gamePhase;
	int splashTime = 0;
	int cursorX;
	int cursorY;
	int redTeamScore = 0;
	int greenTeamScore = 0;
	int winningTeam = 2;
	int topPlayer = 2;

	int serverSenderID = 0;
	int serverRecieverID = 0;
	int previousSender = 0;
	int previousReciever = 0;

	int listen = 0;

	// Game Start Countdown
	int gameStartCountdown = 30;
	int frameCounter = 0;

	// Gameplay Timer // Minute // Seconds
	int gamePlayTimeM = 6;
	int gamePlayTimeS = 0;

	// Is the User entering information for the players
	boolean typing;

	// Player Number ID
	int playerID;

	// Largest Team
	int largestTeam;

	// Player String Code Name
	String playerCodeName;

	// Holdes Square during List Loops
	PlayerSquare playerSquareHolder;

	// Holds Player during List Loops
	Player playerNew;
	Player playerCheck;

	// Array for Squares on Login Screen
	ArrayList<PlayerSquare> squareList;

	// Array for Player Objects
	ArrayList<Player> playerList;

	// Array for ranking players during gameplay.
	ArrayList<Player> redList;

	ArrayList<Player> greenList;

	// Array for Event Log
	ArrayList<Event> eventList;

	// Event Holder
	Event eventHolder;

	// Object for Player Add Window
	PlayerAddWindow playeraddwindow;

	//Database Check
	boolean dataBaseSet = false;

	// Variables for Music Playing MP3
	AdvancedPlayer activeMusic;
	InputStream inputStream;

	// Scanner for Test
	Scanner recieverScan;
	Scanner senderScan;

	String fileName = "Audio/Track01.mp3";
	Music music = new Music(fileName);

	final ConcurrentLinkedQueue<String> mainToServer = new ConcurrentLinkedQueue<String>();
	final ConcurrentLinkedQueue<String> serverToMain = new ConcurrentLinkedQueue<String>();
	String serverMessage;
	String messageToServer;


	// Server Information
	Server server;
	DatagramSocket datagramSocket;

	Model()
	{
		gamePhase = 0;
		cursorX = 105;
		cursorY = 145;
		typing = false;

		// Creates Player Add Window - Starts Hidden
		playeraddwindow = new PlayerAddWindow();

		try {
			datagramSocket = new DatagramSocket(7500);
			server = new Server(datagramSocket, mainToServer, serverToMain);
		} catch (SocketException e) {
			System.out.println("Server is Down");
		}

		// List to hold information for Player and Login Squares and Gameplay Events
		squareList = new ArrayList<PlayerSquare>();
		playerList = new ArrayList<Player>();
		eventList = new ArrayList<Event>();
		redList = new ArrayList<Player>();
		greenList = new ArrayList<Player>();

		// Define Squares for Red Team
		for (int i = 0; i < 15; i++)
		{
		int baseSquareY = 145;
		playerSquareHolder = new PlayerSquare(145,baseSquareY + (i*30));
		squareList.add(playerSquareHolder);
		}

		// Define Squares for Green Team
		for (int i = 0; i < 15; i++)
		{
		int baseSquareY = 145;
		playerSquareHolder = new PlayerSquare(680,baseSquareY + (i*30));
		squareList.add(playerSquareHolder);
		}
	}

	// Update for Model
	public void update()
	{
		// Start Screen Splash Image Function Frame Rate is 40 FPS * 3 = 120 Frames
		if (gamePhase == 0)
		{
			if (splashTime > 120)
			{
				gamePhase = 1;
			}
			splashTime += 1;
		}

		// If the user is entering Player Information check the following conditions
		if (typing == true)
		{
			if (playeraddwindow.stage == 4)
			{
			addPlayerToGame();
			}
		}
		// Update the Player Add Window Screen
		playeraddwindow.update();

		if (gamePhase == 2)
		{
			frameCounter += 1;

			if (gameStartCountdown == 14 && frameCounter == 1)
			{
				musicPlay();
			}

			if (frameCounter == 40)
			{
				gameStartCountdown -= 1;
				frameCounter = 0;
			}

			if (gameStartCountdown == 0)
			{
				gamePhase = 3;
				gameStartCountdown = 30;
				frameCounter = 0;
			}
		}

		if (gamePhase == 3)
		{
			frameCounter += 1;

			if (frameCounter == 0 && gamePlayTimeM == 6)
			{
				for (int i = 0; i < 3; i++)
				{
					messageToServer = "221";
					mainToServer.add(messageToServer);
				}
			}

			if (frameCounter == 35 && gamePlayTimeS == 0)
			{
				gamePlayTimeS = 59;
				frameCounter = 0;
				gamePlayTimeM -= 1;
			}

			if (frameCounter == 35 && gamePlayTimeS != 0)
			{
				gamePlayTimeS -= 1;
				frameCounter = 0;
			}

			if (gamePlayTimeM == 0 && gamePlayTimeS == 0)
			{
				gamePhase = 4;
			}
			countTeamPoints();
			sortScores();
			readServer();
		}
	}

	// Cursor will switch team .. Flip Flop Design
	public void switchTeams()
	{
		// Check if Red Team if True go to Green Team
		if (cursorX == 105)
		{
			// Set Cursor at top of team
			cursorY = 145;

			// Move Cursor over to Green
			cursorX = 640;
			
			// Lower Cursor if the spot has already been taken
			for (int i = 0; i < squareList.size(); i++)
			{
				playerSquareHolder = squareList.get(i);

				if (playerSquareHolder.usedSquare == true && playerSquareHolder.playerSquareX == 680)
				{
					cursorY += 30;
				}
			}
		}
		
		// Check Green Team is True Move over to Red Team
		else
		{
			// Set Cursor to the top of the team
			cursorY = 145;

			// Set Cursor to the Red Team
			cursorX = 105;
			
			// Lower Cursor if the spot has already been taken
			for (int i = 0; i < squareList.size(); i++)
			{
				playerSquareHolder = squareList.get(i);

				if (playerSquareHolder.usedSquare == true && playerSquareHolder.playerSquareX == 145)
				{
					cursorY += 30;
				}
			}
		}
	}

	// Resets Player Screen
	void clearTeams()
	{
		// Reset Player Squares
		for (int i = 0; i < squareList.size(); i++)
		{
			playerSquareHolder = squareList.get(i);
			playerSquareHolder.usedSquare = false;
		}

		// Reset Player List
		playerList.clear();

		// Return Cursor to the starting point of the program
		cursorX = 105;
		cursorY = 145;
	}

	void endGame()
	{
		// Clear Events
		eventList.clear();

		musicStop();

		// Set Game Time back to 6 minutes
		gamePlayTimeM = 6;
		gamePlayTimeS = 0;

		redList.clear();
		greenList.clear();

		for (int i = 0; i < playerList.size(); i++)
		{
			playerNew = playerList.get(i);
			playerNew.currentScore = 0;
		}

		gamePhase = 1;
	}

	void countTeamPoints()
	{
		int redTeamScoreLocal = 0;
		int greenTeamScoreLocal = 0;

		for (int i = 0; i < playerList.size(); i++)
			{
				playerNew = playerList.get(i);

				if (playerNew.team == 0)
				{
					redTeamScoreLocal += playerNew.currentScore;
				}
				else
				{
					greenTeamScoreLocal += playerNew.currentScore;
				}
			}

			redTeamScore = redTeamScoreLocal;
			greenTeamScore = greenTeamScoreLocal;

			if (redTeamScore > greenTeamScore)
			{
				winningTeam = 0;
			}
			
			if (redTeamScore < greenTeamScore)
			{
				winningTeam = 1;
			}

			if (greenTeamScore == redTeamScore)
			{
				winningTeam = 2;
			}
	}

	// Rearrange the Ranking List Dependent on current score.
	void sortScores()
	{
		Collections.sort(redList, Collections.reverseOrder());
		Collections.sort(greenList, Collections.reverseOrder());
		findTopPlayer();
	}

	void readServer()
	{
		serverMessage = serverToMain.poll();

		if (serverMessage != null)
		{
			String[] parts = serverMessage.split(":");
			if (parts.length == 2) 
			{
				serverSenderID = Integer.parseInt(parts[0]);
				serverRecieverID = Integer.parseInt(parts[1]);
				codeNameHitEvent(serverSenderID, serverRecieverID);
			}
		}
	}


	void findTopPlayer()
	{
		// Compare Top Players on Both Teams both teams must have atleast 1 player
		if (redList.size() > 0 && greenList.size() > 0)
		{
			playerNew = redList.get(0);

			playerCheck = greenList.get(0);

		if (playerNew.currentScore > playerCheck.currentScore)
			{
				topPlayer = 0;
			}
		else if (playerNew.currentScore < playerCheck.currentScore)
			{
				topPlayer = 1;
			}
		else
			{
				topPlayer = 2;
			}
	}

		// Check the player is top on own team RED
		if (topPlayer == 0 && redList.size() > 1)
		{
			playerCheck = redList.get(1);
			if (playerCheck.currentScore == playerNew.currentScore)
			{
				topPlayer = 2;
			}
		}

		// Check the player is top on own team GREEN
		if (topPlayer == 1 && greenList.size() > 1)
		{
			playerNew = greenList.get(1);
			if (playerCheck.currentScore == playerNew.currentScore)
			{
				topPlayer = 2;
			}
		}
	}

	void musicPlay()
	{
		music.play();
	}

	void musicStop()
	{
		music.close();
	}

	void openAddPlayerWindow()
	{
		playeraddwindow.windowOpen = true;
		playeraddwindow.stage = 1;
		typing = true;
	}

	void getPlayerInfoTest()
	{
		for (int i = 0; i < playerList.size(); i++)
			{
				playerNew = playerList.get(i);
				
				System.out.println(playerNew.playerCodeName + " " + playerNew.equipmentID);
			}
	}


	// Create Event that one player hit another
	void codeNameHitEvent(int serverSenderID, int serverRecieverID)
	{
		String senderCodeName = "";
		String recieverCodeName = "";
		String event = "hit";

		/* Old Scanner Testing Code
		int senderEquiptmentID;
		int recieverEquiptmentID;
		senderScan = new Scanner(System.in);
		recieverScan = new Scanner(System.in);

		System.out.println("Enter Sender Equitment ID");
		senderEquiptmentID = senderScan.nextInt();

		System.out.println("Enter Reciever Equitment ID");
		recieverEquiptmentID = recieverScan.nextInt();
		*/

		for (int i = 0; i < playerList.size(); i++)
		{
			playerNew = playerList.get(i);

			if (playerNew.equipmentID == serverSenderID)
			{
				senderCodeName = playerNew.playerCodeName;

				if (serverRecieverID == 43 && serverSenderID % 2 != 0)
				{
					playerNew.currentScore += 100;
					recieverCodeName = "Green Base";
					playerNew.setScoredOnBase(true);
				}

				else if (serverRecieverID == 53 && serverSenderID % 2 == 0)
				{
					playerNew.currentScore += 100;
					recieverCodeName = "Red Base";
					playerNew.setScoredOnBase(true);
				}

				else if (serverRecieverID % 2 != serverSenderID % 2)
				{
					playerNew.currentScore += 10;
				}
				else if (serverRecieverID != 43 && serverRecieverID != 53)
				{
					event = "Friendly Fire";
				}
			}

			if (playerNew.equipmentID == serverRecieverID)
			{
				recieverCodeName = playerNew.playerCodeName;
			}
		}

		if (recieverCodeName != "" && senderCodeName != "")
		{
	    eventHolder = new Event(recieverCodeName, senderCodeName, event);
		eventList.add(eventHolder);
		}
	}

	// Check the size of each team to determine the size of the Play Windows
	int teamSize()
	{
		int redTeamSize = 0;
		int greenTeamSize = 0;
		int lTeam = 0;

		for (int i = 0; i < playerList.size(); i++)
			{
				playerNew = playerList.get(i);

				if (playerNew.menux == 105)
				{
					redTeamSize += 1;
				}
				else
				{
					greenTeamSize += 1;
				}
			}

		if (redTeamSize > greenTeamSize)
		{
			lTeam = redTeamSize;
		}
		else
		{
			lTeam = greenTeamSize;
		}

		return lTeam;
	}

	// Move Game Forward to Phase 2 and Gameplay
	void startGame()
	{
		// Create List for Player Rankings
		for (int i = 0; i < playerList.size(); i++)
			{
				playerNew = playerList.get(i);

				if (playerNew.team == 0)
				{
					redList.add(playerNew);
				}
				
				if (playerNew.team == 1)
				{
					greenList.add(playerNew);
				}
			}

		largestTeam = teamSize();
		gamePhase = 2;
	}

	// Add Player to Game after entering Equitment ID in PlayerAddWindow
	void addPlayerToGame()
	{
		playerCodeName = playeraddwindow.windowCodeName;
		playerID = Integer.valueOf(playeraddwindow.windowPlayerID);

		playerNew = new Player(playerID, playerCodeName, cursorX + 30, cursorY);

		// Add Player to a Team
		if (cursorX == 105)
		{
		playerNew.team = 0;
		}
		else
		{
		playerNew.team = 1;
		}

		// Add Equiptment to Player Instance
		playerNew.equipmentID = Integer.valueOf(playeraddwindow.windowEquipment);

		String idStringOut = "" + playerID;
		if(SupabaseOperations.read("id", idStringOut).getID() == 0)
		{
			SupabaseOperations.create(playerNew);
		}
		
		playerList.add(playerNew);

		// Update Squares
		for (int i = 0; i < squareList.size(); i++) 
		{
			playerSquareHolder = squareList.get(i);

			if (playerSquareHolder.playerSquareX == cursorX + 40 && playerSquareHolder.playerSquareY == cursorY) {
				playerSquareHolder.usedSquare = true;
			}
		}
		
		// Move Cursor Down to the next Player Square or move it to the other team if
		// this team is full.
		if (cursorY < 536) {
			cursorY += 30;
		} else {
			switchTeams();
		}

		// Return to first sceen with the Player Add Window returned to stage 0
		typing = false;
		playeraddwindow.windowOpen = false;
		playeraddwindow.stage = 0;
	}
}
