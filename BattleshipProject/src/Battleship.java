public class Battleship {
	public static void main(String[] args) throws Exception{

		GameBoard playerBoard = new GameBoard();

		GameBoard aiBoard = new GameBoard();
		Ai Cp = new Ai();
		Cp.placeShips(aiBoard.getBoard(), aiBoard);

		GameWindow window = new GameWindow("Battleship", playerBoard, aiBoard);
		window.CreateWindow();		
	}
}
