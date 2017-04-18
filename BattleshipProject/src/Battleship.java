import java.util.Scanner;

public class Battleship {
    public static void main(String[] args) {
        
    	GameBoard Game = new GameBoard();
    	int playerBoard[][] = Game.Create();
    	Scanner keyboard = new Scanner(System.in);
    	int row = 0;
    	int col = 0;
    	char direction = 0;
    	
    	while(Game.getShipNum() != 0){
    		System.out.print("Enter the row to place your " + Game.GetShip() + ": ");
    		row = keyboard.nextInt();
    		
    		System.out.print("Enter the column to place your " + Game.GetShip() + ": ");
    		col = keyboard.nextInt();
    		
    		System.out.print("Enter the direction to place your " + Game.GetShip() + "(up,down,left,right): ");
    		direction = keyboard.next().charAt(0);
    		direction = Character.toLowerCase(direction);
    		
    		System.out.println();
    		Game.AddShip(playerBoard, direction, row, col);
    		Game.Print(playerBoard);
    	}
    }
}