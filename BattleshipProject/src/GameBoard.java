
public class GameBoard {
	
private int shipSize;
private int shipType;
private int shipNum;
private String ships[];
private int rowSize;
private int colSize;
private int placementFail;

public String GetShip(){ return ships[0]; }
public int checkPlacement(){ return placementFail; }
public int getShipNum(){ return shipNum; }

public GameBoard(){
	ships = new String[] {"Carrier","Battleship","Cruiser","Submarine","Destroyer"};
	shipSize = 0;
	shipType = 0;
	rowSize = 10;
	colSize = 10;
	shipNum = 5;
	placementFail = 0;
}

public int[][] Create(){
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

public int placementCheck(int arr [][], char direction, int row, int col, int shipSize){
	//Checks if ship placement is out of bounds
	int check = 0;
	if(row >=0 && row <= rowSize && col >= 0 && col <= colSize){
		if(direction == 'u' && (row-shipSize >= 0)){
			check = 1;
		}
		else if(direction == 'd' && (rowSize-(shipSize-1) >= row)){
			check = 1;
		}
		else if(direction == 'l' && (col-shipSize >= 0)){
			check = 1;
		}
		else if(direction == 'r' && (colSize-(shipSize-1) >= col)){
			check = 1;
		}
		else{
			System.out.println("You ship is out of the board. Please Enter in new values.");
			check = 0;
		}
	}	
	else{
		System.out.println("You ship is out of the board. Please Enter in new values.");
		check = 0;
		return check;
	}
	//Checks if ship placement overlap other ships
	switch(direction){
		case 'u':
			for(int y = row; y > row - shipSize; y--){
				if(arr[y-1][col-1] != 0){
					check = 0;
					System.out.println("You ship overlaps another ship. Please enter in new values.");
					break;
				}
			}
			break;
		case 'd':
			for(int y = row; y < row + shipSize; y++){
				if(arr[y-1][col-1] != 0){
					check = 0;
					System.out.println("You ship overlaps another ship. Please enter in new values.");
					break;
				}
			}
			break;
		case 'l':
			for(int x = col; x > col - shipSize; x--){
				if(arr[row - 1][x-1] != 0){
					check = 0;
					System.out.println("You ship overlaps another ship. Please enter in new values.");
					break;
				}
			}
			break;
		case 'r':
			for(int x = col; x < col + shipSize; x++){
				if(arr[row-1][x-1] != 0){
					check = 0;
					System.out.println("You ship overlaps another ship. Please enter in new values.");
					break;
				}
			}
			break;
		default:
			check = 0;
			break;
	}
	
	return check;
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
			shipType = 2;
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

public void shipsResize(){
	//Go to the next ship on the list
	String newShips[] = new String[ships.length-1];
	for(int x = 0; x < newShips.length; x++){
		newShips[x] = ships[x+1];
	}
	ships = newShips.clone();
	shipNum--;
}

public int[][] AddShip(int arr[][], char direction, int row, int col){
	//If the check passes, then decide what ship to place
	placementFail = 0;
	DetermineShip();
	int check = placementCheck(arr, direction, row, col, shipSize);
	if(check == 1){
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
				placementFail = 1;
				return arr;
		}
		return arr;
	}
	else{
		placementFail = 1;
		return arr;
	}
}
}
