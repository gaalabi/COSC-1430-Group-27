import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PlayerButton extends JButton implements ActionListener, MouseListener{
	private ImageIcon waves, red, blue, current, rotImage;
	private double rotU, rotD, rotL;//assumes image faces right or east of the screen
	private int rLoc, cLoc;
	private GameWindow window;
	
	public PlayerButton(GameWindow frame, int r, int c){
		addMouseListener(this);
		addActionListener(this);
		this.setMargin(new Insets(0,0,0,0));
		waves = new ImageIcon(this.getClass().getResource("waveB.png"));
		red = new ImageIcon(this.getClass().getResource("test1.png"));
		blue = new ImageIcon(this.getClass().getResource("test2.png"));
		rotImage = new ImageIcon();
		rotU = -90;
		rotD = 90;
		rotL = 180;
		window = frame;
		rLoc = r;
		cLoc = c;
		current = waves;
		setIcon(current);
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
				rotImage = rotateImage(red,rot);
				break;
			case 2:
				rotImage = rotateImage(red,rot);
				break;
			case 3:
				rotImage = rotateImage(red,rot);
				break;
			case 4:
				rotImage = rotateImage(red,rot);
				break;
			case 5:
				rotImage = rotateImage(red,rot);
				break;
			}
			break;
		case 2:
			switch (shipPart) {
			case 1:
				rotImage = rotateImage(red,rot);
				break;
			case 2:
				rotImage = rotateImage(red,rot);
				break;
			case 3:
				rotImage = rotateImage(red,rot);
				break;
			case 4:
				rotImage = rotateImage(red,rot);
				break;
			}
			break;
		case 3:
			switch (shipPart) {
			case 1:
				rotImage = rotateImage(red,rot);
				break;
			case 2:
				rotImage = rotateImage(red,rot);
				break;
			case 3:
				rotImage = rotateImage(red,rot);
				break;
			}
			break;
		case 4:
			switch (shipPart) {
			case 1:
				rotImage = rotateImage(red,rot);
				break;
			case 2:
				rotImage = rotateImage(red,rot);
				break;
			case 3:
				rotImage = rotateImage(red,rot);
				break;
			}
			break;
		case 5:
			switch (shipPart) {
			case 1:
				rotImage = rotateImage(red,rot);
				break;
			case 2:
				rotImage = rotateImage(red,rot);
				break;
			}
			break;
		}
	}
	
	public void showCurrent(){
		setIcon(current);
	}
	
	public void actionPerformed(ActionEvent e) {
		window.placeShip(rLoc, cLoc);
		current = red;
		window.setShipInfo();
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
