import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.awt.Font;

class View extends JPanel
{
	Model model;
	PlayerSquare playerSquare;
	Player playerInfo;
	Controller controller;
	BufferedImage photonSplash;
	BufferedImage photonPlayerLogin;
	BufferedImage photonGameplay;

	View(Controller c, Model m)
	{
		model = m;
		try
		{
    		this.photonSplash = ImageIO.read(new File("Photon_Resize.jpg"));
			this.photonPlayerLogin = ImageIO.read(new File("Photon_Player_Select15.png"));
			this.photonGameplay = ImageIO.read(new File("Photon_Gameplay_Placeholder.jpg"));
		}
		catch(Exception e) 
		{
    		e.printStackTrace(System.err);
    		System.exit(1);
		}
	}

	public void paintComponent(Graphics g)
	{

		// Draw the Photon Splash Screen
		if (model.gamePhase == 0)
		{
			g.drawImage(this.photonSplash, 0, 0, this.getWidth(), this.getHeight(), null);
		}

		if (model.gamePhase == 1)
		{

			// Set Text For Names
			g.setFont(new Font("Georgia", Font.PLAIN, 25));

			// Draw Static Background Image
			g.drawImage(this.photonPlayerLogin, 0, 0, this.getWidth(), this.getHeight(), null);
			
			// Draw and Check the Squares
			for (int i = 0; i < model.squareList.size(); i++)
			{
			
			playerSquare = model.squareList.get(i);
			
			// Draws Player Square White if Blank
			if (playerSquare.usedSquare == false)
			{
				g.setColor(new Color(255,255,255));
				g.fillRect(playerSquare.playerSquareX, playerSquare.playerSquareY, 10, 10);
			}
			
			// Draws Player Square Black if Spot is Used
			if (playerSquare.usedSquare == true)
			{
				g.setColor(new Color(0,0,0));
				g.fillRect(playerSquare.playerSquareX, playerSquare.playerSquareY, 10, 10);
			}

			}

			// Draw the Cursor
			g.setColor(new Color(0, 0, 0));
			g.fillRect(model.cursorX, model.cursorY,20,10);
			

			// Print the user information on dashboard
			for (int i = 0; i < model.playerList.size(); i++)
			{
				// Sets Text Color to Black
				g.setColor(new Color(0,0,0));

				// Holder for Player List
				playerInfo = model.playerList.get(i);

				// Converts Int value of ID to a String
				String playerIDText = Integer.toString(playerInfo.playerID);

				// Draws PlayerID
				g.drawString(playerIDText,playerInfo.menux + 100,playerInfo.menuy+10);

				// Draws PlayerCodeName
				g.drawString(playerInfo.playerCodeName, playerInfo.menux+300, playerInfo.menuy+10);
			}
		}

		if (model.gamePhase == 2)
		{
			g.drawImage(this.photonGameplay, 0, 0, this.getWidth(), this.getHeight(), null);
		}

	}
}
