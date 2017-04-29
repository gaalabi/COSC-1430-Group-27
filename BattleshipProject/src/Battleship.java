public class Battleship {
public static void main(String[] args) throws Exception{
        
    	GameBoard playerBoard = new GameBoard();
    	int row = 0;
    	int col = 0;
    	char direction;
    	    	
    	GameBoard aiBoard = new GameBoard();
    	Ai Cp = new Ai();
    	Cp.placeShips(aiBoard.getBoard(), aiBoard);
    	aiBoard.Print();
    	
    	GameWindow window = new GameWindow("Battleship", playerBoard, aiBoard);
    	window.CreateWindow();
    }
}
