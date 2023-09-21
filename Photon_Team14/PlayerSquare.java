public class PlayerSquare {
    int playerSquareX;
    int playerSquareY;
    int playerSquareW;
    int playerSquareH;
    boolean usedSquare;

    PlayerSquare(int playerSquareX, int playerSquareY)
    {
        this.playerSquareX = playerSquareX;
        this.playerSquareY = playerSquareY;
        playerSquareH = 10;
        playerSquareW = 10;
        usedSquare = false;
    }
    
}
