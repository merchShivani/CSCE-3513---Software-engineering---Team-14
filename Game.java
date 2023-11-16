import javax.swing.JFrame;
import java.awt.Toolkit;
import java.net.DatagramSocket;



public class Game extends JFrame
{
	Model model;
	View view;
	Controller controller;
	Server server;
	DatagramSocket datagramSocket;

	public static volatile int threadInt = 0;

	public Game()
	{

		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		
		this.setTitle("Photon");
		this.setSize(1280, 720);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.addKeyListener(controller);
		this.setLocationRelativeTo(null);
	}

	// Starting Point of the Entire Program
	public static void main(String[] args)
	{
		// Create Game along with Model, Controller and View
		Game g = new Game();

		// Start a While Loop that will not end until the user exits or there is a error
		g.run();
	}


	// While Loop that goes until the user exits out of the screen
	public void run()
{
	while(true)
	{
		

		//Run the Update Functions for the Controller, Model and View
		controller.update();
		model.update();
		view.repaint(); // This will indirectly call View.paintComponent
		Toolkit.getDefaultToolkit().sync(); // Updates screen

		// Go to sleep for 25 milliseconds this equals about 40 fps
		try
		{
			Thread.sleep(25);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}

}
