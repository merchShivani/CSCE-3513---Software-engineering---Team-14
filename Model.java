import java.util.ArrayList;

class Model
{
	int gamePhase;
	int splashTime = 0;
	int cursorX;
	int cursorY;
	int redTeamScore = 0;
	int greenTeamScore = 0;

	// Game Start Countdown
	int gameStartCountdown = 30;
	int secondCounter = 0;

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

	// Array for Squares on Login Screen
	ArrayList<PlayerSquare> squareList;

	// Array for Player Objects
	ArrayList<Player> playerList;

	// Array for Event Log
	ArrayList<Event> eventList;

	// Event Holder
	Event eventHolder;

	// Object for Player Add Window
	PlayerAddWindow playeraddwindow;

	//Database Check
	boolean dataBaseSet = false;

	Model()
	{
		gamePhase = 0;
		cursorX = 105;
		cursorY = 145;
		typing = false;

		// Creates Player Add Window - Starts Hidden
		playeraddwindow = new PlayerAddWindow();

		// List to hold information for Player and Login Squares and Gameplay Events
		squareList = new ArrayList<PlayerSquare>();
		playerList = new ArrayList<Player>();
		eventList = new ArrayList<Event>();

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
			if (playeraddwindow.stage == 2)
			{
				addPlayerID();
			}

			if (playeraddwindow.stage == 4 || playeraddwindow.stage == 6)
			{
				createPlayer();
			}

			if (playeraddwindow.stage == 8)
			{
				addEquipmentNumber();
			}
		}
		// Update the Player Add Window Screen
		playeraddwindow.update();

		if (gamePhase == 2)
		{
			secondCounter += 1;

			if (secondCounter == 40)
			{
				gameStartCountdown -= 1;
				secondCounter = 0;
			}

			if (gameStartCountdown == 0)
			{
				gamePhase = 3;
				gameStartCountdown = 30;
			}
		}

		if (gamePhase == 3)
		{
			countTeamPoints();
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

		for (int i = 0; i < playerList.size(); i++)
		{
			playerNew = playerList.get(i);
			playerNew.currentScore = 0;
		}
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
	}


	void openAddPlayerWindow()
	{
		playeraddwindow.windowOpen = true;
		playeraddwindow.stage = 1;
		typing = true;
	}

	// Create Event that one player hit another
	void codenameHit()
	{
		String senderCodeName = " ";
		String recieverCodeName = " ";
		String event = "hit";
	
		// For Testing
		int firstRed = 0;
		int firstGreen = 0;
		int teamsAssign = 0;
		 
		for (int i = 0; i < 2; i++)
			{
				playerNew = playerList.get(i);

				if (playerNew.team == 0 && firstRed == 0)
				{
					senderCodeName = playerNew.playerCodeName;
					firstRed = 1;
				}

				if (playerNew.team == 1 && firstGreen == 0)
				{
					recieverCodeName = playerNew.playerCodeName;
					firstGreen = 1;
				}
			}

		if (firstGreen == 1 && firstRed == 1)
		{
			teamsAssign = 1;
			firstGreen = 2;
			firstRed = 2;
		}

		if (teamsAssign == 1)
		{
			eventHolder = new Event(recieverCodeName, senderCodeName, event);
			eventList.add(eventHolder);
			teamsAssign = 2;
		}


		for (int i = 0; i < eventList.size(); i++)
		{
			eventHolder = eventList.get(i);
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
		largestTeam = teamSize();
		gamePhase = 2;
	}

	// Checks if the new Player ID is equal to an ID already in the List.

	//////										////
	/////  Function for Database Check for ID ////
	/////										/////
	/////									//////

	boolean checkID(int playerID)
	{
		for (int i = 0; i < playerList.size(); i++)
		{
			playerNew = playerList.get(i);

			if (playerID == playerNew.playerID)
			{
				playerCodeName = playerNew.playerCodeName;
				return true;
			}
		}
		return false;
	}

	// Add Player ID and determine if the Player Add Window follows a found ID or needs a new Code Name.
	void addPlayerID()
	{
		playerID = Integer.valueOf(playeraddwindow.windowPlayerID);

		if (checkID(playerID) == true)
		{
			playeraddwindow.stage = 3;
		}
		else
		{
			playeraddwindow.stage = 5;
		}
	}

	void addEquipmentNumber()
	{
		playerNew.equipmentID = Integer.valueOf(playeraddwindow.windowEquipment);

			// Update Squares
			for (int i = 0; i < squareList.size(); i++)
		{
			playerSquareHolder = squareList.get(i);

			if (playerSquareHolder.playerSquareX == cursorX + 40 && playerSquareHolder.playerSquareY == cursorY)
			{
				playerSquareHolder.usedSquare = true;
			}
		}

		// Move Cursor Down to the next Player Square or move it to the other team if this team is full.
		if (cursorY < 536)
		{
			cursorY += 30;
		}
		else
		{
			switchTeams();
		}

		// Return to first sceen with the Player Add Window returned to stage 0
		typing = false;
		playeraddwindow.windowOpen = false;
		playeraddwindow.stage = 0;
	}

	//Creates new Player Object based on Player Add Window stage if ID was found use copy to create new game player
	// If player ID not found use the text defined in the Player Add Window second text field.

	void createPlayer()
	{
		if (playeraddwindow.stage == 6)
		{
		playerCodeName = playeraddwindow.windowCodeName;
		playerID = Integer.valueOf(playeraddwindow.windowPlayerID);
		}

		playerNew = new Player(playerID, playerCodeName, cursorX+30, cursorY);

		// Add Player to a Team
		if (cursorX == 105)
		{
		playerNew.team = 0;
		}
		else
		{
		playerNew.team = 1;
		}

		// Add Player to the game instance list.
		playerList.add(playerNew);

		playeraddwindow.stage = 7;
	}

	void databaseAddPlayers(int playerID, String playerCodeName)
	{
		playerNew = new Player(playerID, playerCodeName, cursorX + 30, cursorY);
		playerList.add(playerNew);

		// Update Squares
		for (int i = 0; i < squareList.size(); i++) {
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
