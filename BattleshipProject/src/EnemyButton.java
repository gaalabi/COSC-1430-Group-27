import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class EnemyButton extends JButton implements ActionListener{
	private ImageIcon waves, wavehit, wavemiss, current;
	private ImageIcon[] explo;
	private GameWindow window;
	private GameBoard EnemyBoard;
	private Timer timer;
	private int rLoc, cLoc, count;
	
	private TimerTask explosion = new TimerTask(){
		public void run(){
			if(count < explo.length){
				setIcon(explo[count]);
				count++;
			}
			else{
				count = 0;
				shotStatus();
				timer.cancel();
			}
		}
	};
	
	public EnemyButton(GameWindow frame, GameBoard eBoard, int r, int c){
		this.addActionListener(this);
		this.setMargin(new Insets(0,0,0,0));
		window = frame;
		EnemyBoard = eBoard;
		rLoc = r;
		cLoc = c;
		count = 0;
		current = waves;
		explo = new ImageIcon[14];
		timer = new Timer();
		setImages();
		setIcon(waves);
	}
	
	public void setImages(){
		waves = new ImageIcon(this.getClass().getResource("waveB.png"));
		wavehit = new ImageIcon(this.getClass().getResource("/explosion/wblackmarkhit.png"));
		wavemiss = new ImageIcon(this.getClass().getResource("wmiss.png"));
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
	}
		
	public void shotStatus(){
		if(EnemyBoard.getHitoMiss(rLoc, cLoc)){
			current = wavehit;
			setIcon(current);
		}
		else{
			current = wavemiss;
			setIcon(current);
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		if(EnemyBoard.checkSpace(rLoc, cLoc)){
			EnemyBoard.hitORmiss(rLoc, cLoc);
			timer = new Timer();
			timer.scheduleAtFixedRate(explosion, 80, 80);
			EnemyBoard.Print();
		}
	}

}
