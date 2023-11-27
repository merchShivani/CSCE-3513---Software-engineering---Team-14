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

		if (model.gamePhase == 4)
		{
			if (e.getKeyCode() == KeyEvent.VK_SPACE)
			{
				model.endGame();
			}
		}

		/* 
		if (model.gamePhase == 3)
		{
			if (e.getKeyCode() == KeyEvent.VK_Q)
			{
				model.getPlayerInfoTest();
			}
		}
		*/
	}

	
	public void keyTyped(KeyEvent e)
	{

	}

	public void update()
	{
	
	}

}
