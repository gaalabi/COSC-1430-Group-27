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
		
		
		Complayer opponent = new Complayer();
		opponent.setInitialSpace();

		System.out.println(opponent.getR()+" "+opponent.getC());

		boolean canHit = aiBoard.checkSpace(opponent.getR(), opponent.getC());

		if (canHit == true)
		{
			aiBoard.hitORmiss(opponent.getR(),opponent.getC());
			aiBoard.Print();
		}
		opponent.setR(1);
		opponent.setC(1);


		boolean hit = aiBoard.getHitoMiss(opponent.getR(), opponent.getC());

		if(hit == hit)
		{
			System.out.println(opponent.getR()+" "+opponent.getC());
			opponent.shipFound(1, aiBoard.getBoard());
			aiBoard.hitORmiss(opponent.getR(), opponent.getC());
			aiBoard.Print();
		}

		int i = 3;
		while( i > 0)
		{
			opponent.dirFound('d', aiBoard.getBoard());
			aiBoard.hitORmiss(opponent.getR(), opponent.getC());
			aiBoard.Print();
			i--;
		}

		opponent.returnToCenter();

		System.out.println(opponent.getR()+" "+opponent.getC());

		opponent.makeSure(aiBoard.getBoard());

		aiBoard.hitORmiss(opponent.getR(), opponent.getC());

		aiBoard.Print();
	}
}
