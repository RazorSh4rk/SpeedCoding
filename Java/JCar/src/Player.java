import java.awt.Color;

import javax.swing.JPanel;

public class Player extends JPanel{
	public int x, y;
	public Player() {
		this.setSize(StaticContainer.PLAYER_W, StaticContainer.PLAYER_H);
		this.setBackground(Color.GREEN);
		x = 145;
		y = 420;
	}
}
