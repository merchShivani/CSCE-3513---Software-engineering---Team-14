import java.util.ArrayList;

class Model
{
	int gamePhase;
	int splashTime = 0;
	int cursorX;
	int cursorY;

	// Is the User entering information for the players
	boolean typing;

	// Player Number ID
	int playerID;

	// Player String Code Name
	String playerCodeName;

	int count;

	// Holdes Square during List Loops
	PlayerSquare playerSquareHolder;

	// Holds Player during List Loops
	Player playerNew;

	// Array for Squares on Login Screen
	ArrayList<PlayerSquare> squareList;

	// Array for Player Objects
	ArrayList<Player> playerList;

	// Object for Player Add Window
	PlayerAddWindow playeraddwindow;

	Model()
	{
		gamePhase = 0;
		cursorX = 105;
		cursorY = 145;
		typing = false;

		// Creates Player Add Window - Starts Hidden
		playeraddwindow = new PlayerAddWindow();

		// List to hold information for Player and Login Squares
		squareList = new ArrayList<PlayerSquare>();
		playerList = new ArrayList<Player>();


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
		}

		// Update the Player Add Window Screen
		playeraddwindow.update();
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

	void openPlayerWindow()
	{
		playeraddwindow.windowOpen = true;
		playeraddwindow.stage = 1;
		typing = true;
	}

	// Move Game Forward to Phase 2 and Gameplay
	void startGame()
	{
		gamePhase = 2;
	}

	// Checks if the new Player ID is equal to an ID already in the List.
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

	//Creates new Player Object based on Player Add Window stage if ID was found use copy to create new game player
	// If player ID not found use the text defined in the Player Add Window second text field.

	void createPlayer()
	{
		
		if (playeraddwindow.stage == 6)
		{
		playerCodeName = playeraddwindow.windowCodeName;
		}

		playerNew = new Player(playerID, playerCodeName, cursorX+30, cursorY);
		playerList.add(playerNew);

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
}
