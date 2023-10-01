public class AddPlayersToGame {
    public static void main(String[] args) {
        // Create a game instance
        Game game = new Game();

        // Start the game loop
        game.run();

        // Simulate adding two players
        addPlayersToGame(game.getModel());

        // Close the game after adding players (you can adjust this based on your game logic)
        game.closeGame();
    }

    // Simulate adding two players to the game
    private static void addPlayersToGame(Model model) {
        // Open the player entry window
        model.openPlayerWindow();

        // Simulate adding Player 1
        model.setplayerID("1"); // Set the player ID as needed
        model.setplayerCodeName("Player1"); // Set the player code name as needed
        model.createPlayer();

        // Simulate adding Player 2
        model.setplayerID("2"); // Set the player ID as needed
        model.setplayerCodeName("Player2"); // Set the player code name as needed
        model.createPlayer();
    }
}
