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

	PlayerSquare playerSquare;
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


		// Squares for Red Team
		for (int i = 0; i < 16; i++)
		{
		int baseSquareY = 145;
		playerSquare = new PlayerSquare(145,baseSquareY + (i*30));
		squareList.add(playerSquare);
		}

		// Squares for Green Team
		for (int i = 0; i < 16; i++)
		{
		int baseSquareY = 145;
		playerSquare = new PlayerSquare(680,baseSquareY + (i*30));
		squareList.add(playerSquare);
		}


	}

	// Update for Model
	public void update()
	{
		// Start Screen
		if (gamePhase == 0)
		{
			if (splashTime > 100)
			{
				gamePhase = 1;
			}
			splashTime += 1;
		}
	}


	// Cursor goes down Y axis
	public void moveCursorDown()
	{
		if (cursorY < 595)
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

	// Cursor will switch team
	public void switchTeams()
	{
		if (cursorX == 105)
		{
			cursorX = 650;
		}
		else
		{
			cursorX = 105;
		}
	}

	// Typing for the player
	public void typePlayer()
	{
		typing = true;
		System.out.println("Please enter players ID number.");
		playerID = intObj.nextInt();

		//Check if ID is within database.
		


		System.out.println("Please enter players codename.");
		playerCodeName = typingObj.nextLine();
		typing = false;

		playerNew = new Player(playerID, playerCodeName, cursorX+30, cursorY);
		playerList.add(playerNew);

		for (int i = 0; i < squareList.size(); i++)
		{
			playerSquare = squareList.get(i);

			if (playerSquare.playerSquareX == (cursorX + 30) && playerSquare.playerSquareY == cursorY)
			{
				playerSquare.usedSquare = true;
			}
		}

		cursorY += 30;
	}
}
