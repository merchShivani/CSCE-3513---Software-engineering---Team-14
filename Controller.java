import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


class Controller implements KeyListener
{
	Model model;

	Controller(Model m)
	{
		model = m;
	}

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			
		}
	}

	public void keyReleased(KeyEvent e)
	{
		if (model.gamePhase == 1)
		{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_SHIFT: model.switchTeams(); break;
			case KeyEvent.VK_SPACE: model.openAddPlayerWindow(); break;
			case KeyEvent.VK_Z: model.clearTeams(); break;
			case KeyEvent.VK_CONTROL: model.startGame(); break;
			case KeyEvent.VK_ESCAPE: System.exit(0);
		}
		}

		if (model.gamePhase == 3)
		{
			if (e.getKeyCode() == KeyEvent.VK_SPACE)
			{
				model.gamePhase = 1;
				model.endGame();
			} 
		}

		if (model.gamePhase == 3)
		{
			// Use A key while in Gameplay to add 10 points to all players and print an event.
			if (e.getKeyCode() == KeyEvent.VK_A)
			{
				for (int i = 0; i < model.playerList.size(); i++)
			{
				model.playerNew = model.playerList.get(i);
				model.playerNew.currentScore += 10;
				
				// Need Player on both teams to active the event.
				if (model.playerList.size() > 1)
				{
				model.codenameHit();
				}
			}
			}
		}
	}

	
	public void keyTyped(KeyEvent e)
	{

	}

	public void update()
	{
	
	}

}
