import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class EnemyButton extends JButton implements ActionListener{
	private ImageIcon waves;
	
	public EnemyButton(){
		this.addActionListener(this);
		this.setMargin(new Insets(0,0,0,0));
		waves = new ImageIcon(this.getClass().getResource("waveB.png"));
		setIcon(waves);
	}
	public void actionPerformed(ActionEvent e) {
				
	}

}
