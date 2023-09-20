import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;

class View extends JPanel
{
	Model model;
	Controller controller;
	BufferedImage photonSplash;
	BufferedImage photonPlayerLogin;

	View(Controller c, Model m)
	{
		model = m;
		try
		{
    		this.photonSplash = ImageIO.read(new File("Photon_Resize.jpg"));
			this.photonPlayerLogin = ImageIO.read(new File("Photon_Player_Select.jpg"));
		}
		catch(Exception e) 
		{
    		e.printStackTrace(System.err);
    		System.exit(1);
		}
	}

	public void paintComponent(Graphics g)
	{
    	g.setColor(new Color(128, 255, 255));
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		// Draw the Photon Splash Screen

		if (model.gamePhase == 0)
		{
			g.drawImage(this.photonSplash, 0, 0, this.getWidth(), this.getHeight(), null);
		}

		if (model.gamePhase == 1)
		{
			g.drawImage(this.photonPlayerLogin, 0, 0, this.getWidth(), this.getHeight(), null);
			
			g.fillRect(ALLBITS, ABORT, WIDTH, HEIGHT);
		
		
		}

	}
}
