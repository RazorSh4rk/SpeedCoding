import java.awt.Color;

import javax.swing.JPanel;

public class Block extends JPanel{
	public int x, y;
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
		this.setSize(StaticContainer.BLOCK_W, StaticContainer.BLOCK_H);
		this.setBackground(Color.RED);
	}
	public void move(int i) {
		if(y < StaticContainer.GAME_H)
			y += i;
	}
}
