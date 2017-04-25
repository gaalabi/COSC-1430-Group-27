import java.util.Random;

public class Ai {
	private int row;
	private int col;
	private char[] directionList;
	private char direction;
	private int leftEdge;
	private int rightEdge;
	private int topEdge;
	private int botEdge;
	
	Random rand;
	public Ai(){
		row = 0;
		col = 0;
		rand = new Random();
		directionList = new char[] {'u','d','l','r'};
		leftEdge = 1;
		rightEdge = 1;
		topEdge = 1;
		botEdge = 1;
	}
	
	public void checkShipEdge(char direction, GameBoard Game, int row, int col, int shipSize){
		//Check if the ship is on any of the edges of the board
		leftEdge = 1;
		rightEdge = 1;
		topEdge = 1;
		botEdge = 1;
		int rowSize = Game.getRowSize();
		int colSize = Game.getColSize();
		
		switch(direction){
			case 'u':
				if(col == 1){
					leftEdge = 0;
				}
				else if(col == colSize){
					rightEdge = 0;
				}
				
				if(row == rowSize){
					botEdge = 0;
				}
				else if((row - shipSize + 1) == 1){
					topEdge = 0;
				}
				break;
			case 'd':
				if(col == 1){
					leftEdge = 0;
				}
				else if(col == colSize){
					rightEdge = 0;
				}
				
				if((row + shipSize - 1) == rowSize){
					botEdge = 0;
				}
				else if(row == 1){
					topEdge = 0;
				}
				break;
			case 'l':
				if((col - shipSize + 1) == 1){
					leftEdge = 0;
				}
				else if(col == colSize){
					rightEdge = 0;
				}
				
				if(row == rowSize){
					botEdge = 0;
				}
				else if(row == 1){
					topEdge = 0;
				}
				break;
			case 'r':
				if(col == 1){
					leftEdge = 0;
				}
				else if((col + shipSize - 1) == colSize){
					rightEdge = 0;
				}
				
				if(row == rowSize){
					botEdge = 0;
				}
				else if(row == 1){
					topEdge = 0;
				}
				break;
			default:
				break;
		}
	}
	
	public boolean goodPlacement(int[][] arr, GameBoard Game, char direction, int row, int col){
		//Check if placement is not next or diagonal to any other ship
		int shipSize = Game.getShipSize();
		checkShipEdge(direction, Game, row, col, shipSize);
		if(direction == 'u'){
			for(int r = (row + botEdge); r >= (row - shipSize + 1 - topEdge); r--){
				for(int c = (col - leftEdge); c <= (col + rightEdge); c++){
					if(arr[r-1][c-1] != 0){
						return false;
					}
				}
			}
		}
		else if(direction == 'd'){
			for(int r = (row - topEdge); r <= (row + shipSize - 1 + botEdge); r++){
				for(int c = (col - leftEdge); c <= (col + rightEdge); c++){
					if(arr[r-1][c-1] != 0){
						return false;
					}
				}
			}
		}
		else if(direction == 'l'){
			for(int r = (row - topEdge); r <= (row + botEdge); r++){
				for(int c = (col + rightEdge); c >= (col - shipSize + 1 - leftEdge); c--){
					if(arr[r-1][c-1] != 0){
						return false;
					}
				}
			}
		}
		else if(direction == 'r'){
			for(int r = (row - topEdge); r <= (row + botEdge); r++){
				for(int c = (col - leftEdge); c <= (col + shipSize - 1 + rightEdge); c++){
					if(arr[r-1][c-1] != 0){
						return false;
					}
				}
			}
		}
		return true;
	}
		
	public int[][] placeShips(int arr[][], GameBoard Game){
		//Have the Ai place all it's ship on the board
		int shipNum = Game.getShipNum();
		while(Game.getShipNum() != (shipNum - 1)){
			row = rand.nextInt(10)+1;
			col = rand.nextInt(10)+1;
			direction = directionList[rand.nextInt(4)];
			Game.AddShip(direction, row, col);
		}
		
		while(Game.getShipNum() != 0){
			row = rand.nextInt(10)+1;
			col = rand.nextInt(10)+1;
			direction = directionList[rand.nextInt(4)];
			Game.placementCheck(direction, row, col, Game.getShipSize());
			if(Game.getValid() == 1){
				if(goodPlacement(arr, Game, direction, row, col)){
					Game.AddShip(direction, row, col);
				}				
			}
		}
		return arr;
	}
}
