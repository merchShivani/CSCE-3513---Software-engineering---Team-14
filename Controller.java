import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;


class Controller implements ActionListener, KeyListener
{
	View view;
	Model model;

	boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keyTab;

	Controller(Model m)
	{
		model = m;
	}

	public void actionPerformed(ActionEvent e)
	{
		
	}

	void setView(View v)
	{
		view = v;
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
			//case KeyEvent.VK_DOWN: model.moveCursorDown(); break;
			//case KeyEvent.VK_UP: model.moveCursorUp(); break;
			case KeyEvent.VK_SHIFT: model.switchTeams(); break;
			case KeyEvent.VK_SPACE: model.typePlayer(); break;
			case KeyEvent.VK_Z: model.clearTeams(); break;
			case KeyEvent.VK_CONTROL: model.startGame(); break;
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
