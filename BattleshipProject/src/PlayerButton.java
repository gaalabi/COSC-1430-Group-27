import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PlayerButton extends JButton implements ActionListener, MouseListener{
	private ImageIcon waves, current, rotImage, wavehit, wavemiss;
	private ImageIcon car1, car2, car3, car4, car5;
	private ImageIcon bs1, bs2, bs3, bs4;
	private ImageIcon cru1, cru2, cru3;
	private ImageIcon sub1, sub2, sub3;
	private ImageIcon des1, des2;
	private ImageIcon[] explo;
	private double rotU, rotD, rotL;//assumes image faces right or east of the screen
	private int rLoc, cLoc, count;
	private GameState gameState;
	private GameWindow window;
	private Timer timer;
	private GameBoard playerBoard, EnemyBoard;
	private boolean shotShown;
	
	public ImageIcon getRotImage(){ return rotImage; }
	
	private TimerTask explosion = new TimerTask(){
		public void run(){
			if(count < explo.length){
				setIcon(explo[count]);
				count++;
			}
			else{
				count = 0;				
				window.shootPlayer();
				shotStatus();
				window.setShotDone(true);
				timer.cancel();
			}
		}
	};
	
	public PlayerButton(GameWindow frame, GameBoard pBoard, GameBoard eBoard, GameState gState, int r, int c){
		addMouseListener(this);
		addActionListener(this);
		this.setMargin(new Insets(0,0,0,0));
		explo = new ImageIcon[14];
		setImages();
		rotImage = new ImageIcon();
		rotU = -90;
		rotD = 90;
		rotL = 180;
		window = frame;
		rLoc = r;
		cLoc = c;
		gameState = gState;
		current = waves;
		playerBoard = pBoard;
		EnemyBoard = eBoard;
		shotShown = false;
		timer = new Timer();
		setIcon(current);
	}
	
	public void setImages(){
		waves = new ImageIcon(this.getClass().getResource("waveB.png"));
		car1 = new ImageIcon(this.getClass().getResource("/ships/Carrier/car1.png"));
		car2 = new ImageIcon(this.getClass().getResource("/ships/Carrier/car2.png"));
		car3 = new ImageIcon(this.getClass().getResource("/ships/Carrier/car3.png"));
		car4 = new ImageIcon(this.getClass().getResource("/ships/Carrier/car4.png"));
		car5 = new ImageIcon(this.getClass().getResource("/ships/Carrier/car5.png"));
		bs1 = new ImageIcon(this.getClass().getResource("/ships/Battleship/bs1.png"));
		bs2 = new ImageIcon(this.getClass().getResource("/ships/Battleship/bs2.png"));
		bs3 = new ImageIcon(this.getClass().getResource("/ships/Battleship/bs3.png"));
		bs4 = new ImageIcon(this.getClass().getResource("/ships/Battleship/bs4.png"));
		cru1 = new ImageIcon(this.getClass().getResource("/ships/Cruiser/cru1.png"));
		cru2 = new ImageIcon(this.getClass().getResource("/ships/Cruiser/cru2.png"));
		cru3 = new ImageIcon(this.getClass().getResource("/ships/Cruiser/cru3.png"));
		sub1 = new ImageIcon(this.getClass().getResource("/ships/Submarine/sub1.png"));
		sub2 = new ImageIcon(this.getClass().getResource("/ships/Submarine/sub2.png"));
		sub3 = new ImageIcon(this.getClass().getResource("/ships/Submarine/sub3.png"));
		des1 = new ImageIcon(this.getClass().getResource("/ships/Destroyer/des1.png"));
		des2 = new ImageIcon(this.getClass().getResource("/ships/Destroyer/des2.png"));
		explo[0] = new ImageIcon(this.getClass().getResource("/explosion/wExplo1.png"));
		explo[1] = new ImageIcon(this.getClass().getResource("/explosion/wExplo2.png"));
		explo[2] = new ImageIcon(this.getClass().getResource("/explosion/wExplo3.png"));
		explo[3] = new ImageIcon(this.getClass().getResource("/explosion/wExplo4.png"));
		explo[4] = new ImageIcon(this.getClass().getResource("/explosion/wExplo5.png"));
		explo[5] = new ImageIcon(this.getClass().getResource("/explosion/wExplo6.png"));
		explo[6] = new ImageIcon(this.getClass().getResource("/explosion/wExplo7.png"));
		explo[7] = new ImageIcon(this.getClass().getResource("/explosion/wExplo8.png"));
		explo[8] = new ImageIcon(this.getClass().getResource("/explosion/wExplo9.png"));
		explo[9] = new ImageIcon(this.getClass().getResource("/explosion/wExplo10.png"));
		explo[10] = new ImageIcon(this.getClass().getResource("/explosion/wExplo11.png"));
		explo[11] = new ImageIcon(this.getClass().getResource("/explosion/wExplo12.png"));
		explo[12] = new ImageIcon(this.getClass().getResource("/explosion/wExplo13.png"));
		explo[13] = new ImageIcon(this.getClass().getResource("/explosion/wExplo14.png"));
		wavehit = new ImageIcon(this.getClass().getResource("/explosion/wblackmarkhit.png"));
		wavemiss = new ImageIcon(this.getClass().getResource("wmiss.png"));
	}
	
	public void shotStatus(){
		if(playerBoard.getHitoMiss(rLoc-1, cLoc-1)){
			current = wavehit;
			setIcon(current);
		}
		else{
			current = wavemiss;
			setIcon(current);
		}
	}
	
	public void shotsFired(){
		if(!shotShown && playerBoard.checkSpace(rLoc-1, cLoc-1) && EnemyBoard.getShipNum() == 0 && !gameState.getAiWin() && !gameState.getPlayerWin()){
			window.setShotDone(false);
			timer = new Timer();
			timer.scheduleAtFixedRate(explosion, 80, 80);
			shotShown = true;
			window.updateGameState();
		}
	}
	
	public ImageIcon rotateImage(ImageIcon picture, double angle){
		int w = picture.getIconWidth();
        int h = picture.getIconHeight();
        int type = BufferedImage.TYPE_INT_RGB;
        BufferedImage image = new BufferedImage(h, w, type);
        Graphics2D g2 = image.createGraphics();
        double x = (h - w)/2.0;
        double y = (w - h)/2.0;
        AffineTransform at = AffineTransform.getTranslateInstance(x, y);
        at.rotate(Math.toRadians(angle), w/2.0, h/2.0);
        g2.drawImage(picture.getImage(), at, null);
        g2.dispose();
        picture = new ImageIcon(image);
 
        return picture;
	}
	
	public void drawShipPart(int shipPart, int shipType, char direction){
		setShipImage(shipPart, shipType, direction);
		setIcon(rotImage);
	}
	
	public void setShipImage(int shipPart, int shipType, char direction){
		switch (direction) {
		case 'u':
			rotatePart(shipType, shipPart, rotU);
			break;
		case 'd':
			rotatePart(shipType, shipPart, rotD);
			break;
		case 'l':
			rotatePart(shipType, shipPart, rotL);
			break;
		case 'r':
			rotatePart(shipType, shipPart, 0);
			break;
		default:
			rotatePart(shipType, shipPart, 0);
			break;
		}
	}
	
	public void rotatePart(int shipType, int shipPart, double rot){
		switch (shipType) {
		case 1:
			switch (shipPart) {
			case 1:
				rotImage = rotateImage(car1,rot);
				break;
			case 2:
				rotImage = rotateImage(car2,rot);
				break;
			case 3:
				rotImage = rotateImage(car3,rot);
				break;
			case 4:
				rotImage = rotateImage(car4,rot);
				break;
			case 5:
				rotImage = rotateImage(car5,rot);
				break;
			}
			break;
		case 2:
			switch (shipPart) {
			case 1:
				rotImage = rotateImage(bs1,rot);
				break;
			case 2:
				rotImage = rotateImage(bs2,rot);
				break;
			case 3:
				rotImage = rotateImage(bs3,rot);
				break;
			case 4:
				rotImage = rotateImage(bs4,rot);
				break;
			}
			break;
		case 3:
			switch (shipPart) {
			case 1:
				rotImage = rotateImage(cru1,rot);
				break;
			case 2:
				rotImage = rotateImage(cru2,rot);
				break;
			case 3:
				rotImage = rotateImage(cru3,rot);
				break;
			}
			break;
		case 4:
			switch (shipPart) {
			case 1:
				rotImage = rotateImage(sub1,rot);
				break;
			case 2:
				rotImage = rotateImage(sub2,rot);
				break;
			case 3:
				rotImage = rotateImage(sub3,rot);
				break;
			}
			break;
		case 5:
			switch (shipPart) {
			case 1:
				rotImage = rotateImage(des1,rot);
				break;
			case 2:
				rotImage = rotateImage(des2,rot);
				break;
			}
			break;
		default:
			rotImage = waves;
			break;
		}
	}
	
	public void showCurrent(){
		setIcon(current);
	}
	
	public void setCurrent(ImageIcon newImage){
		current = newImage;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(window.getShipNum() > 0 && !gameState.getAiWin() && !gameState.getPlayerWin()){
			window.placeShip(rLoc, cLoc);
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		
	}
	
	public void mouseEntered(MouseEvent e) {
		window.showShip(rLoc, cLoc);
		window.setOnBoard();
	}
	
	public void mouseExited(MouseEvent e) {
		window.clearShip();
		window.setOnBoard();
	}
	
	public void mousePressed(MouseEvent e) {
		
	}
	
	public void mouseReleased(MouseEvent e) {
		
	}	
}
