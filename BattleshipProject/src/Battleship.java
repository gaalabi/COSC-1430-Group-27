import java.util.Scanner;

public class Battleship {
public static void main(String[] args) throws Exception{
        
    	GameBoard playerBoard = new GameBoard();
		Scanner keyboard = new Scanner(System.in);
    	int row = 0;
    	int col = 0;
    	char direction;
    	
    	/*while(Game.getShipNum() != 5){
    		System.out.print("Enter the row to place your " + Game.GetShip() + ": ");
    		row = keyboard.nextInt();
    		
    		System.out.print("Enter the column to place your " + Game.GetShip() + ": ");
    		col = keyboard.nextInt();
    		
    		System.out.print("Enter the direction to place your " + Game.GetShip() + "(up,down,left,right): ");
    		direction = keyboard.next().charAt(0);
    		direction = Character.toLowerCase(direction);
    		
    		System.out.println();
    		Game.AddShip(playerBoard, direction, row, col);
    		Game.getError();
    		Game.Print(playerBoard);
    	}*/
    	
    	GameBoard aiBoard = new GameBoard();
    	Ai Cp = new Ai();
    	Cp.placeShips(aiBoard.getBoard(), aiBoard);
    	aiBoard.Print();
    	
    	GameWindow window = new GameWindow("Battleship", playerBoard, aiBoard);
    	window.CreateWindow();
    	
    	keyboard.close();
    }
}
