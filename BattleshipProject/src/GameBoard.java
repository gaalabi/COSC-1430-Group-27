
public class GameBoard {
	
	private int shipSize, shipType, shipNum, rowSize, colSize, valid, errorNum;
	private String ships[];
	
	public String GetShip(){ return ships[0]; }
	public int getShipNum(){ return shipNum; }
	public int getValid(){ return valid; }
	public int getRowSize(){ return rowSize; }
	public int getColSize(){ return colSize; }
	public int getShipType(){ return shipType; }
	
	public int getShipSize(){
		DetermineShip();
		return shipSize;
	}
	
	public GameBoard(){
		ships = new String[] {"Carrier","Battleship","Cruiser","Submarine","Destroyer"};
		shipSize = 0;
		shipType = 0;
		rowSize = 10;
		colSize = 10;
		shipNum = 5;
		valid = 0;
		errorNum = 0;
	}
	
	public int[][] CreateBoard(){
		//creates matrix that holds characters
	    int arr[][] = new int[rowSize][colSize];
	    
	    return arr;
	}
	
	public void Print(int arr[][]){
		//prints out matrix
	    for(int row = 0; row < rowSize; row++)
	    {
	        for(int col = 0; col < colSize; col++)
	        {
	            System.out.print(arr[row][col]+"  ");
	           
	        }
	        System.out.println();
	    }
	    System.out.println("\n");
	}
	
	public void getError(){
		if(errorNum == 0){
			System.out.print("");
		}
		else if(errorNum == 1){
			System.out.println("You ship is out of the board. Please Enter in new values.");
		}
		else if(errorNum == 2){
			System.out.println("You ship overlaps another ship. Please enter in new values.");
		}
	}
	
	public int[][] AddShip(int arr[][], char direction, int row, int col){
		//If the valid passes, then decide what ship to place
		errorNum = 0;
		DetermineShip();
		valid = 1;
		placementCheck(arr, direction, row, col, shipSize);
		if(valid == 1){
			switch (ships[0]){
				case "Carrier":
					placeShip(arr, direction, row, col);
					shipsResize();
					break;
				case "Battleship":
					placeShip(arr, direction, row, col);
					shipsResize();
					break;
				case "Cruiser":
					placeShip(arr, direction, row, col);
					shipsResize();
					break;
				case "Submarine":
					placeShip(arr, direction, row, col);
					shipsResize();
					break;
				case "Destroyer":
					placeShip(arr, direction, row, col);
					shipsResize();
					break;
				default:
					valid = 0;
					return arr;
			}
			return arr;
		}
		else{
			valid = 0;
			return arr;
		}
	}
	
	public void DetermineShip(){
		//Determine the next ship type and size
		switch (ships[0]){
			case "Carrier":
				shipType = 1;
				shipSize = 5;
				break;
			case "Battleship":
				shipType = 2;
				shipSize = 4;
				break;
			case "Cruiser":
				shipType = 3;
				shipSize = 3;
				break;
			case "Submarine":
				shipType = 4;
				shipSize = 3;
				break;
			case "Destroyer":
				shipType = 5;
				shipSize = 2;
				break;
			default:
				shipType = 0;
				shipSize = 0;
				break;
		}
	}
	
	public void placementCheck(int arr [][], char direction, int row, int col, int shipSize){
		//checks if chosen row and column is inside the board
		if(row > 0 && row <= rowSize && col > 0 && col <= colSize){
			
			//checks if ship placement is out of bounds
			if(direction == 'u' && (row-shipSize >= 0)){
				overlapCheck(arr, direction, row, col);
			}
			else if(direction == 'd' && (rowSize-(shipSize-1) >= row)){
				overlapCheck(arr, direction, row, col);
			}
			else if(direction == 'l' && (col-shipSize >= 0)){
				overlapCheck(arr, direction, row, col);
			}
			else if(direction == 'r' && (colSize-(shipSize-1) >= col)){
				overlapCheck(arr, direction, row, col);
			}
			else{
				errorNum = 1;
				valid = 0;
			}	
		}	
		else{
			errorNum = 1;
			valid = 0;
		}
	}
	
	public void overlapCheck(int[][] arr, char direction, int row, int col){
		//checks if ship placement overlap other ships
		valid = 1;
		switch(direction){
			case 'u':
				for(int y = row; y > row - shipSize; y--){
					if(arr[y-1][col-1] != 0){
						valid = 0;
						errorNum = 2;
						break;
					}
				}
				break;
			case 'd':
				for(int y = row; y < row + shipSize; y++){
					if(arr[y-1][col-1] != 0){
						valid = 0;
						errorNum = 2;
						break;
					}
				}
				break;
			case 'l':
				for(int x = col; x > col - shipSize; x--){
					if(arr[row - 1][x-1] != 0){
						valid = 0;
						errorNum = 2;
						break;
					}
				}
				break;
			case 'r':
				for(int x = col; x < col + shipSize; x++){
					if(arr[row-1][x-1] != 0){
						valid = 0;
						errorNum = 2;
						break;
					}
				}
				break;
			default:
				valid = 0;
				break;
		}
	}
	
	public int[][] placeShip(int arr[][], char direction, int row, int col){
		//Places the ship on the board in a certain direction
		switch(direction){
			case 'u':
				for(int y = row; y > row - shipSize; y--){
					arr[y-1][col-1] = shipType;
				}
				break;
			case 'd':
				for(int y = row; y < row + shipSize; y++){
					arr[y-1][col-1] = shipType;
				}
				break;
			case 'l':
				for(int x = col; x > col - shipSize; x--){
					arr[row - 1][x-1] = shipType;
				}
				break;
			case 'r':
				for(int x = col; x < col + shipSize; x++){
					arr[row-1][x-1] = shipType;
				}
				break;
			default:
				return arr;
		}
		
		return arr;
	}
	
	public void shipsResize(){
		//Go to the next ship on the list
		String newShips[] = new String[ships.length-1];
		for(int x = 0; x < newShips.length; x++){
			newShips[x] = ships[x+1];
		}
		ships = newShips.clone();
		shipNum--;
	}
}
