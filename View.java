import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.Color;
import java.awt.Font;

class View extends JPanel {
	Model model;
	PlayerSquare playerSquare;
	Player playerInfo;
	Controller controller;
	BufferedImage photonSplash;
	BufferedImage photonPlayerLogin;
	BufferedImage photonGameplay;

	View(Controller c, Model m) {
		model = m;
		try {
			this.photonSplash = ImageIO.read(new File("Photon_Resize.jpg"));
			this.photonPlayerLogin = ImageIO.read(new File("Photon_Player_Select15.png"));
			this.photonGameplay = ImageIO.read(new File("Photon_Gameplay_Placeholder.jpg"));
		} catch (Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
	}

	public void paintComponent(Graphics g) {

		// Draw the Photon Splash Screen
		if (model.gamePhase == 0) {
			g.drawImage(this.photonSplash, 0, 0, this.getWidth(), this.getHeight(), null);
		}

		if (model.gamePhase == 1) {

			// Set Text For Names
			g.setFont(new Font("Georgia", Font.PLAIN, 25));

			// Draw Static Background Image
			g.drawImage(this.photonPlayerLogin, 0, 0, this.getWidth(), this.getHeight(), null);

			// Draw and Check the Squares
			for (int i = 0; i < model.squareList.size(); i++) {

				playerSquare = model.squareList.get(i);

				// Draws Player Square White if Blank
				if (playerSquare.usedSquare == false) {
					g.setColor(new Color(255, 255, 255));
					g.fillRect(playerSquare.playerSquareX, playerSquare.playerSquareY, 10, 10);
				}

				// Draws Player Square Black if Spot is Used
				if (playerSquare.usedSquare == true) {
					g.setColor(new Color(0, 0, 0));
					g.fillRect(playerSquare.playerSquareX, playerSquare.playerSquareY, 10, 10);
				}

			}

			// Draw the Cursor
			g.setColor(new Color(0, 0, 0));
			g.fillRect(model.cursorX, model.cursorY, 20, 10);

			// Print the user information on dashboard
			for (int i = 0; i < model.playerList.size(); i++) {
				// Sets Text Color to Black
				g.setColor(new Color(0, 0, 0));

				// Holder for Player List
				playerInfo = model.playerList.get(i);

				// Converts Int value of ID to a String
				String playerIDText = Integer.toString(playerInfo.playerID);

				// Draws PlayerID
				g.drawString(playerIDText, playerInfo.menux + 100, playerInfo.menuy + 10);

				// Draws PlayerCodeName
				g.drawString(playerInfo.playerCodeName, playerInfo.menux + 300, playerInfo.menuy + 10);
			}
		}

		if (model.gamePhase == 2) {
			String counterText = Integer.toString(model.gameStartCountdown);
			g.setColor(new Color(250, 0, 0));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(new Color(250, 250, 250));
			g.setFont(new Font("Times New Roman", Font.BOLD, 200));
			g.drawString(counterText, 550, 350);
		}

		if (model.gamePhase == 3) {
			int teamBoxHeight = model.largestTeam * 30 + 60;
			g.setFont(new Font("Times New Roman", Font.BOLD, 20));
			g.setColor(new Color(0, 0, 0));
			g.fillRect(0, 0, this.getWidth(), this.getHeight());

			// Draw Events Window
			g.setColor(new Color(0, 0, 250));
			g.fillRect(100, 50, 1100, 600);

			// Draw Teams Square
			g.setColor(new Color(250, 250, 250));
			g.fillRect(100, 50, 1100, teamBoxHeight);

			// Draw Red Team Score
			g.setColor(new Color(250, 0, 0));
			String redTeamScore = Integer.toString(model.redTeamScore);
			g.drawString("Red Team Score: ", 200, teamBoxHeight + 30);
			g.drawString(redTeamScore, 400, teamBoxHeight + 30);

			// Draw Green Team Score
			g.setColor(new Color(0, 250, 0));
			String greenTeamScore = Integer.toString(model.greenTeamScore);
			g.drawString("Green Team Score: ", 750, teamBoxHeight + 30);
			g.drawString(greenTeamScore, 950, teamBoxHeight + 30);

			// Outline for game Area
			g.setColor(new Color(200, 200, 200));
			g.fillRect(100, 50, 20, 600);
			g.fillRect(1200, 50, 20, 600);
			g.fillRect(100, 50, 1100, 20);
			g.fillRect(100, 650, 1120, 20);

			// Line Between Team Window and Events Window
			g.fillRect(100, teamBoxHeight + 40, 1100, 20);

			for (int i = 0; i < model.playerList.size(); i++) {
				playerInfo = model.playerList.get(i);

				if (playerInfo.team == 0) {
					g.setColor(new Color(250, 0, 0));
				} else {
					g.setColor(new Color(0, 250, 0));
				}
				// Holder for Player List

				// Converts Int value of ID to a String
				String playerScore = Integer.toString(playerInfo.currentScore);

				// Draws PlayerScore
				g.drawString(playerScore, playerInfo.menux + 200, playerInfo.menuy - 50);

				// Draws PlayerCodeName
				g.drawString(playerInfo.playerCodeName, playerInfo.menux + 100, playerInfo.menuy - 50);
			}

			g.setColor(new Color(255, 255, 255));


		}
	}
}
