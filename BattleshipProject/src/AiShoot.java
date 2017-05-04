import java.util.Random;

public class AiShoot {
	private int row, col, initialRow, initialCol, row0, col0, test, lastRow, lastCol;
	private int[] dirNum;
	private Random rand;
	private boolean rowEven, colEven, currentHit, dirFound, lastHit, shipFound, doubleCheck, border;
	private GameBoard playerBoard;
	private char dir;

	public int getRow(){ return row; }
	public int getCol(){ return col; }

	AiShoot(GameBoard pBoard){
		rand = new Random();
		playerBoard = pBoard;
		initialRow = 0;
		initialCol = 0;
		row0 = rand.nextInt(10);
		col0 = rand.nextInt(10);
		row = row0;
		col = col0;
		lastRow = row;
		lastCol = col;
		currentHit = false;
		lastHit = false;
		dirFound = false;
		border = false;
		shipFound = false;
		doubleCheck = true;
		dir = 'u';
		test = 1;
		rowcolEven();
	}

	public void decideShotLoc(){
		/*if(doubleCheck && shipFound){
			if(!playerBoard.getHitoMiss(row, col)){
				dirFound = false;
			}
			else{
				dirFound = true;
			}
		}*/
		
		System.out.println("\nborder:" + border);
		if(border){
			lastHit = false;
			border = false;
		}
		else{
			setLastHit();
		}
		
		if(dirFound && !lastHit){
			resetTest();
			shipFound = false;
			dirFound = false;
			doubleCheck = false;
		}
		System.out.println("Going in:" + "\nlastRow:" + lastRow + "\nlastCol:" + lastCol + "\nrow:" + row + "\ncol:" + col + "\nlastHit:" + lastHit + "\nshipFound:" + shipFound + "\ndirFound:" + dirFound + "\ndoubleCheck:" + doubleCheck);
		if(!lastHit && !shipFound && doubleCheck){
			System.out.println("HIIII");
			while(!playerBoard.checkSpace(row, col)){
				setRowCol2();
			}
		}			
		if(doubleCheck && (lastHit || shipFound)){
			if(!shipFound){
				setInitial();
			}
			shipFound = true;
			
			if(lastHit && !dirFound){			
				testNextDirection();
			}
			else if(!lastHit){
				testNextDirection();
			}
			else if(lastHit && dirFound){
				finishHer();
			}
			
			if(playerBoard.checkIsShip(row, col)){
				dirFound = true;
			}
			else{
				dirFound = false;
			}
		}
		else if(!doubleCheck){
			setToInitial();
			changeDir();
			finishHer();
			doubleCheck = true;
			if(playerBoard.checkIsShip(row, col)){
				dirFound = true;
				shipFound = true;
			}
			else{
				if(!playerBoard.checkSpace(row, col)){
					setRowCol2();
				}				
			}
		}
		
		if(!playerBoard.checkSpace(row, col)){
			while(!playerBoard.checkSpace(row, col)){
				setRowCol2();
			}
		}
		System.out.println("\nGoing out:" + "\nlastRow:" + lastRow + "\nlastCol:" + lastCol + "\nrow:" + row + "\ncol:" + col + "\nlastHit:" + lastHit + "\nshipFound:" + shipFound + "\ndirFound:" + dirFound + "\ndoubleCheck:" + doubleCheck);
	}
	
	public void setCurrentHit(){
		currentHit = playerBoard.getHitoMiss(row,col);
	}
	
	public void setLastHit(){
		lastRow = row;
		lastCol = col;
		lastHit = playerBoard.getHitoMiss(lastRow, lastCol);
	}
	
	private void rowcolEven(){
		if(row0%2 == 0){
			rowEven = true;
		}
		else{
			rowEven = false;
		}

		if(col0%2 == 0){
			colEven = true;
		}
		else{
			colEven = false;
		}
	}
	
	public void setRowCol2(){
		row = rand.nextInt(10);
		col = rand.nextInt(10);
	}
	
	public void setRowCol(){
		row = rand.nextInt(10);
		col = rand.nextInt(10);
		if(rowEven){
			while(row%2 != 0){
				row = rand.nextInt(10);
			}
		}
		else{
			while(row%2 == 0){
				row = rand.nextInt(10);
			}
		}

		if(colEven){
			while(col%2 != 0){
				col = rand.nextInt(10);
			}
		}
		else{
			while(col%2 == 0){
				col = rand.nextInt(10);
			}
		}
	}

	public void setInitial(){
		initialRow = row;
		initialCol = col;
	}

	public void setToInitial(){
		row = initialRow;
		col = initialCol;
	}

	public void testNextDirection(){
		do{
			setToInitial();
			switch(test){
			case 1:
				row--;
				test++;
				dir = 'u';
				break;
			case 2:
				row++;
				test++;
				dir = 'd';
				break;
			case 3:
				col++;
				test++;
				dir = 'r';
				break;
			case 4:
				col--;
				resetTest();
				dir = 'l';
				break;
			}
		}while(row >= 10 || row < 0 || col >= 10 || col < 0);
	}
	
	public void finishHer(){
		switch(dir){
		case 'u':
			if(row != 0){
				row--;
			}
			else{
				border = true;
			}			
			break;
		case 'd':
			if(row != 10){
				row++;
			}
			else{
				border = true;
			}
			break;
		case 'l':
			if(col != 0){
				col--;
			}
			else{
				border = true;
			}
			break;
		case 'r':
			if(col != 10){
				col++;
			}
			else{
				border = true;
			}
			break;
		}
	}
	
	public void changeDir(){
		switch(dir){
		case 'u':
			dir = 'd';
			break;
		case 'd':
			dir = 'u';
			break;
		case 'l':
			dir = 'r';
			break;
		case 'r':
			dir = 'l';
			break;
		}
	}
	
	public void resetTest(){
		test = 1;
	}
}
