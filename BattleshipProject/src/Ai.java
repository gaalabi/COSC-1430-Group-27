import java.util.Random;

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
	
	public boolean goodPlacement(int[][] arr, char direction, int row, int col){
		
		return true;
	}
	public int[][] placeShips(int arr[][], GameBoard Game){
		while(Game.getShipNum() != 4){
			row = rand.nextInt(10)+1;
			col = rand.nextInt(10)+1;
			direction = directionList[rand.nextInt(4)];
			Game.AddShip(arr, direction, row, col);
		}
		
		while(Game.getShipNum() != 0){
			row = rand.nextInt(10)+1;
			col = rand.nextInt(10)+1;
			direction = directionList[rand.nextInt(4)];
			Game.placementCheck(arr, direction, row, col, Game.getShipSize());
			if(Game.getValid() == 1){
				if(goodPlacement(arr, direction, row, col)){
					Game.AddShip(arr, direction, row, col);
				}				
			}
		}
		return arr;
	}
}
