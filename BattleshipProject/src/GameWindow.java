import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.*;

public class GameWindow extends JFrame implements MouseWheelListener {
	private int row, col, shipSize, shipType, shipNum, validPlacement, cbrow, cbcol, onBoard;
	private JPanel PlayerBoard = new JPanel();
	private JPanel AiBoard = new JPanel();
	private JPanel GameInfo = new JPanel();
	private JPanel holder = new JPanel();
	private PlayerButton pbutton[][];
	private EnemyButton ebutton[][];
	private char direction;
	private GameBoard playerBoard, EnemyBoard;	
	
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
		playerBoard = PlayerBoard;
		EnemyBoard = AiBoard;
		row = 10;
		col = 10;
		pbutton = new PlayerButton[row][col];
		ebutton = new EnemyButton[row][col];
		direction = 'l';
		onBoard = 0;
		setShipInfo();
		}

	public void CreateWindow() {
		setSize(1300, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PlayerBoard.setLayout(new GridLayout(row, col));
		PlayerBoard.setPreferredSize(new Dimension(500, 500));
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				pbutton[r][c] = new PlayerButton(this, r+1, c+1);
				PlayerBoard.add(pbutton[r][c]);
			}
		}

		AiBoard.setLayout(new GridLayout(row, col));
		AiBoard.setPreferredSize(new Dimension(500, 500));
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				ebutton[r][c] = new EnemyButton();
				AiBoard.add(ebutton[r][c]);
			}
		}

		GameInfo.setLayout(new GridLayout(3, 1));

		ImageIcon icon = new ImageIcon(getClass().getResource("waveB.png"));
		JLabel test = new JLabel("<html>Test<br>hello<html>");
		test.setHorizontalAlignment(test.CENTER);
		test.setIcon(icon);
		GameInfo.add(test);

		test = new JLabel("<html>Test<br>hello<html>");
		test.setHorizontalAlignment(test.CENTER);
		GameInfo.add(test);

		test = new JLabel("<html>Test<br>hello<html>");
		test.setHorizontalAlignment(test.CENTER);
		GameInfo.add(test);

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
				
		setVisible(true);
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
			playerBoard.Print();
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
