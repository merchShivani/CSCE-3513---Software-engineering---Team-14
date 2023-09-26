import java.util.ArrayList;
import java.util.Scanner;

class Model
{
	Controller controller;
	int gamePhase;
	int splashTime = 0;
	int cursorX;
	int cursorY;
	boolean typing;
	int playerID;
	String playerCodeName;
	Scanner typingObj = new Scanner (System.in);
	Scanner intObj = new Scanner (System.in);

	PlayerSquare playerSquareHolder;
	Player playerNew;
	ArrayList<PlayerSquare> squareList;
	ArrayList<Player> playerList;

	Model()
	{
		gamePhase = 0;
		cursorX = 105;
		cursorY = 145;
		typing = false;

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
		// Start Screen Splash Image Function
		if (gamePhase == 0)
		{
			if (splashTime > 100)
			{
				gamePhase = 1;
			}
			splashTime += 1;
		}
	}

	/*  Testing Functions to move the cursor up and down on screen.
	// Cursor goes down Y axis
	public void moveCursorDown()
	{
		if (cursorY < 565)
		{
		cursorY += 30;
		}
	}

	// Cursor goes up Y axis
	public void moveCursorUp()
	{
		if (cursorY > 145)
		{
			cursorY -= 30;
		}
	}
	*/

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

	// Typing for the player
	public void typePlayer()
	{
		// typing is true
		typing = true;
		// Enter Player ID Int
		System.out.println("Please enter player's ID number.");
		playerID = intObj.nextInt();


		//Check if ID is within database.
		//
		//
		//
		

		//Add Player Name
		System.out.println("Please enter player's codename.");
		playerCodeName = typingObj.nextLine();
		typing = false;

		playerNew = new Player(playerID, playerCodeName, cursorX+30, cursorY);

		playerList.add(playerNew);

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

	// Move Game Forward to Phase 2 and Gameplay
	void startGame()
	{
		gamePhase = 2;
	}


}
