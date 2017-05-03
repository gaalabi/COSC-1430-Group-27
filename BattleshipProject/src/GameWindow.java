import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.*;

public class GameWindow extends JFrame implements MouseWheelListener {
	private int row, col, shipSize, shipType, shipNum, validPlacement, cbrow, cbcol, onBoard;
	private JPanel PlayerBoard = new JPanel();
	private JPanel AiBoard = new JPanel();
	private JPanel GameInfo = new JPanel();
	private JTextPane helpInfo = new JTextPane();
	private JPanel holder = new JPanel();
	private JLabel bsWinLose;
	private PlayerButton pbutton[][];
	private EnemyButton ebutton[][];
	private char direction;
	private GameBoard playerBoard, EnemyBoard;
	private GameState gameState;
	private ImageIcon win;
	private Complayer cpShoot;
	private boolean cpHit, shipFound;

	public char getDirection(){ return direction; }
	public int getShipType(){ return shipType; }
	public int getShipNum(){ return shipNum; }
	public void setOnBoard(){
		if(onBoard == 1){
			onBoard = 0;
		}
		else{
			onBoard = 1;
		}
	}

	public GameWindow(String title, GameBoard PlayerBoard, GameBoard AiBoard) {
		super(title);
		addMouseWheelListener(this);
		win = new ImageIcon(getClass().getResource("waveB.png"));
		playerBoard = PlayerBoard;
		EnemyBoard = AiBoard;
		gameState = new GameState();
		cpShoot = new Complayer();
		row = 10;
		col = 10;
		pbutton = new PlayerButton[row][col];
		ebutton = new EnemyButton[row][col];
		direction = 'l';
		onBoard = 0;
		cpHit = false;
		shipFound = false;
		setShipInfo();
		cpShoot.setInitialSpace();
	}

	public void CreateWindow() {
		setSize(1400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PlayerBoard.setLayout(new GridLayout(row, col));
		PlayerBoard.setPreferredSize(new Dimension(500, 500));
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				pbutton[r][c] = new PlayerButton(this, gameState, r+1, c+1);
				PlayerBoard.add(pbutton[r][c]);
			}
		}

		AiBoard.setLayout(new GridLayout(row, col));
		AiBoard.setPreferredSize(new Dimension(500, 500));
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				ebutton[r][c] = new EnemyButton(this, gameState, EnemyBoard, playerBoard, r+1, c+1);
				AiBoard.add(ebutton[r][c]);
			}
		}

		GameInfo.setLayout(new GridLayout(3, 1));
		
		bsWinLose = new JLabel("Battleship");
		bsWinLose.setHorizontalAlignment(bsWinLose.CENTER);
		GameInfo.add(bsWinLose);
		
		helpInfo.setBounds(1, 1, 300, 200);
		GameInfo.add(helpInfo, BorderLayout.SOUTH);

		JLabel Board1 = new JLabel("<html>Player<br>Board<html>");
		Board1.setVerticalAlignment(Board1.CENTER);

		JLabel Board2 = new JLabel("<html>Enemy<br>Board<html>");
		Board2.setHorizontalAlignment(Board2.CENTER);
		
		holder.add(Board1);
		holder.add(PlayerBoard);
		holder.add(GameInfo);
		holder.add(AiBoard);
		holder.add(Board2);
		add(holder);
		
		helpInfo.setText("Use mousewheel to turn the ship.\nClick on the Boards to fire and place ships.");

		setVisible(true);
	}
	
	public void shootPlayer() {
		playerBoard.hitORmiss(cpShoot.getR(), cpShoot.getC());
		cpHit = playerBoard.getHitoMiss(cpShoot.getR(), cpShoot.getC());
		if(!cpHit && !shipFound){
			while(!playerBoard.checkSpace(cpShoot.getR(), cpShoot.getC())){
				cpShoot.shipSearch(playerBoard.getBoard());
			}
		}
		else{
			shipFound = true;
			cpShoot.shipFound(1, playerBoard.getBoard());
		}
	}
	
	public void updateGameState(){
		gameState.setWinStatus(playerBoard.getBoard(), EnemyBoard.getBoard());
		if(gameState.getAiWin()){
			gameWin();
		}
		else if(gameState.getPlayerWin()){
			gameWin();
		}
	}
	
	public void gameWin(){
		if(gameState.getPlayerWin() == true){
			win = new ImageIcon(getClass().getResource("pwin.png"));
		}
		else{
			win = new ImageIcon(getClass().getResource("cpwin.png"));
		}
		bsWinLose.setIcon(win);
		bsWinLose.setText("");
	}
	
	public void setShipInfo(){
		playerBoard.DetermineShip();
		shipType = playerBoard.getShipType();
		shipSize = playerBoard.getShipSize();
		shipNum = playerBoard.getShipNum();
	}

	public void showShip(int r, int c){
		int shipPart = 1;
		cbrow = r;
		cbcol = c;
		switch(direction){
		case 'u':
			for(int y = r; y > r - shipSize; y--){
				if(y-1<10 && c-1<10 && y-1>=0 && c-1>=0){
					pbutton[y-1][c-1].drawShipPart(shipPart, shipType, direction);
					shipPart++;
				}
			}
			break;
		case 'd':
			for(int y = r; y < r + shipSize; y++){
				if(y-1<10 && c-1<10 && y-1>=0 && c-1>=0){
					pbutton[y-1][c-1].drawShipPart(shipPart, shipType, direction);
					shipPart++;
				}
			}
			break;
		case 'l':
			for(int x = c; x > c - shipSize; x--){
				if(r-1<10 && x-1<10 && r-1>=0 && x-1>=0){
					pbutton[r-1][x-1].drawShipPart(shipPart, shipType, direction);					
					shipPart++;					
				}
			}
			break;
		case 'r':
			for(int x = c; x < c + shipSize; x++){
				if(r-1<10 && x-1<10 && r-1>=0 && x-1>=0){
					pbutton[r-1][x-1].drawShipPart(shipPart, shipType, direction);
					shipPart++;
				}
			}
			break;
		}
	}

	public void placeShip(int r, int c){
		int shipPart = 1;
		playerBoard.AddShip(direction, r, c);

		if(playerBoard.getValid() == 1){		
			switch(direction){
			case 'u':
				for(int y = r; y > r - shipSize; y--){
					if(y-1<10 && c-1<10 && y-1>=0 && c-1>=0){
						pbutton[y-1][c-1].drawShipPart(shipPart, shipType, direction);
						pbutton[y-1][c-1].setCurrent(pbutton[y-1][c-1].getRotImage());
						shipPart++;
					}
				}
				break;
			case 'd':
				for(int y = r; y < r + shipSize; y++){
					if(y-1<10 && c-1<10 && y-1>=0 && c-1>=0){
						pbutton[y-1][c-1].drawShipPart(shipPart, shipType, direction);
						pbutton[y-1][c-1].setCurrent(pbutton[y-1][c-1].getRotImage());
						shipPart++;
					}
				}
				break;
			case 'l':
				for(int x = c; x > c - shipSize; x--){
					if(r-1<10 && x-1<10 && r-1>=0 && x-1>=0){
						pbutton[r-1][x-1].drawShipPart(shipPart, shipType, direction);
						pbutton[r-1][x-1].setCurrent(pbutton[r-1][x-1].getRotImage());
						shipPart++;					
					}
				}
				break;
			case 'r':
				for(int x = c; x < c + shipSize; x++){
					if(r-1<10 && x-1<10 && r-1>=0 && x-1>=0){
						pbutton[r-1][x-1].drawShipPart(shipPart, shipType, direction);
						pbutton[r-1][x-1].setCurrent(pbutton[r-1][x-1].getRotImage());
						shipPart++;
					}
				}
				break;
			}
			setShipInfo();
		}
	}

	public void clearShip(){
		for(int r=0; r<row; r++){
			for(int c=0; c<col; c++){
				pbutton[r][c].showCurrent();
			}
		}			
	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		if(!e.isConsumed()){
			e.consume();
		}

		if(e.getWheelRotation() > 0){
			switch (direction) {
			case 'u':
				direction = 'r';
				break;
			case 'r':
				direction = 'd';
				break;
			case 'd':
				direction = 'l';
				break;
			case 'l':
				direction = 'u';
				break;
			}
		}
		else if(e.getWheelRotation() < 0){
			switch (direction) {
			case 'u':
				direction = 'l';
				break;
			case 'l':
				direction = 'd';
				break;
			case 'd':
				direction = 'r';
				break;
			case 'r':
				direction = 'u';
				break;
			}
		}

		if(onBoard == 1){
			clearShip();
			showShip(cbrow, cbcol);
		}		
	}
}
