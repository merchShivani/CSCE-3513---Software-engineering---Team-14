public class Player {
    int playerID;
    String playerCodeName;

    int menux;
    int menuy;

    int highscore;
    int equipmentID;
    int currentScore = 0;

    // Red == 0, Green == 1
    int team;

    Player(int playerID, String playerCodeName, int menux, int menuy)
    {
        this.playerID = playerID;
        this.playerCodeName = playerCodeName;
        this.menux = menux;
        this.menuy = menuy;
    }
    
}
