import java.util.Random;
import java.util.stream.IntStream;

public class Ai {
	private int row;
	private int col;
	private char[] directionList;
	private char direction;
	
	Random rand;
	public Ai(){
		row = 0;
		col = 0;
		rand = new Random();
		directionList = new char[] {'u','d','l','r'};		
	}
	
	public int[][] placeShips(int arr[][], GameBoard Game){
		while(Game.getShipNum() != 4){
			row = rand.nextInt(10)+1;
			col = rand.nextInt(10)+1;
			direction = directionList[rand.nextInt(4)];
			System.out.println(direction + " row: " + row + " col: " + col);
			Game.AddShip(arr, direction, row, col);
		}
		return arr;
	}
}
