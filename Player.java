public class Player implements Comparable <Player> {
    int playerID;
    String playerCodeName;
    String playerFirstName;
    String playerLastName;
    int highscore;

    int menux;
    int menuy;
    
    int equipmentID;
    int currentScore = 0;

    // Red == 0, Green == 1
    int team;

    private boolean hasScoredOnBase = false;

    Player() {
        this.playerID = -1;
        this.playerCodeName = "";
        this.playerFirstName = "";
        this.playerLastName = "";
        this.highscore = -1;
        this.menux = -1;
        this.menuy = -1;
        this.hasScoredOnBase = false;
    }

    Player(int playerID, String playerCodeName, int menux, int menuy)
    {
        this();
        this.playerID = playerID;
        this.playerCodeName = playerCodeName;
        this.menux = menux;
        this.menuy = menuy;
    }

    Player(int playerID, String playerCodeName, String playerFirstName, String playerLastName, int highscore)
    {
        this(playerID, playerCodeName, 0, 0);
        this.playerFirstName = playerFirstName;
        this.playerLastName = playerLastName;
        this.highscore = highscore;
    }

    @Override
    public int compareTo(Player player)
    {
        return this.currentScore - player.currentScore;
    }


    public void SetID(int id) {this.playerID = id;}
    public void SetCodeName(String codeName) {this.playerCodeName = codeName;}
    public void SetPlayerFirstName(String firstName) {this.playerFirstName = firstName;}
    public void SetPlayerLastName(String lastName) {this.playerLastName = lastName;}
    public void SetHighScore(int highScore) {this.highscore = highScore;}
    public void SetEquipmentID(int equipmentID) {this.equipmentID = equipmentID;}
    public void SetCurrentScore(int currentScore) {this.currentScore = currentScore;}

    public void SetMenuX(int x) {this.menux = x;}
    public void SetMenuY(int y) {this.menuy = y;}
    public void SetTeam(int team) {this.team = team;}

    public void addToCurrentScore(int pointsToAdd) {this.currentScore += pointsToAdd;}
    public void subtractFromCurrentScore(int pointsToSubtract) {this.currentScore -= pointsToSubtract;}

    public int getID() {return playerID;}
    public String getCodeName() {return playerCodeName;}
    public String getPlayerFirstName() {return playerFirstName;}
    public String getPlayerLastName() {return playerLastName;}
    public int getHighScore() {return highscore;}
    public int getEquipmentID() {return equipmentID;}
    public int getCurrentScore() {return currentScore;}


    
    public boolean hasScoredOnBase() {
        return hasScoredOnBase;
    }

    public void setScoredOnBase(boolean hasScored) {
        this.hasScoredOnBase = hasScored;
    }

    @Override
    public String toString() {
        return "Player ID: " + playerID +
            ", Code Name: " + playerCodeName +
            ", First Name: " + playerFirstName +
            ", Last Name: " + playerLastName +
            ", Menu X: " + menux +
            ", Menu Y: " + menuy +
            ", High Score: " + highscore +
            ", Equipment ID: " + equipmentID +
            ", Current Score: " + currentScore +
            ", Team: " + (team == 0 ? "Red" : "Green");
    }
}
