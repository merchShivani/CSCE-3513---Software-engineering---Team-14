import javax.swing.JFrame;
import java.awt.Toolkit;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.net.URI;
//import java.net.http.HttpClient;
//import java.io.IOException;

public class Game extends JFrame
{
	Model model;
	View view;
	Controller controller;
	PlayerAddWindow playerAddWindow;




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
	public static void main(String[] args) throws Exception
	{
		
		/* 
		HttpRequest request = HttpRequest.newBuilder()
		.uri(URI.create("https://jwrerfjonxuewavojuol.supabase.co"))
		.header("SupaBase-Project-Host","https://supabase.com")
		.header("SupaBase-Project-API","eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Imp3cmVyZmpvbnh1ZXdhdm9qdW9sIiwicm9sZSI6ImFub24iLCJpYXQiOjE2OTM5Mjk3MTksImV4cCI6MjAwOTUwNTcxOX0.SjFvVcdGI3xMjtEsj7SaJlebubfDr65WVVoI1jI9Pqg")
		.method("GET", HttpRequest.BodyPublishers.noBody())
		.build();
		*/

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
