class Model
{
	Controller controller;
	int gamePhase = 0;
	int splashTime = 0;

	Model()
	{
		gamePhase = 0;
	}

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


}
