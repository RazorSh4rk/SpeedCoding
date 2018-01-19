import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame{
	Tile[][] tiles;
	GridLayout layout;
	JPanel panel;
	
	public Game() {
		setSize(Value.I * 50, Value.J * 50);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Mines");
		
		panel = new JPanel();
		layout = new GridLayout(Value.I, Value.J);
		tiles = new Tile[Value.I][Value.J];
		panel.setLayout(layout);
		panel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		
		for(int i = 0; i < Value.I; i++) {
			for(int j = 0; j < Value.J; j++) {
				tiles[i][j] = new Tile();
			}
		}
		for(int i = 0; i < Value.I; i++) {
			for(int j = 0; j < Value.J; j++) {
				if(i == 0) {
					if(j == 0) { //top left
						tiles[i][j].setNum(tiles[i+1][j]);
						tiles[i][j].setNum(tiles[i][j+1]);
						tiles[i][j].setNum(tiles[i+1][j+1]);
					}else if(j == Value.J - 1) { //top right
						tiles[i][j].setNum(tiles[i+1][j]);
						tiles[i][j].setNum(tiles[i][j-1]);
						tiles[i][j].setNum(tiles[i+1][j-1]);
					}else { //top row
						tiles[i][j].setNum(tiles[i][j+1]);
						tiles[i][j].setNum(tiles[i][j-1]);
						tiles[i][j].setNum(tiles[i+1][j]);
						tiles[i][j].setNum(tiles[i+1][j+1]);
						tiles[i][j].setNum(tiles[i+1][j-1]);
					}
				}else if(j == 0 && i < Value.I - 1 && i > 0) { //left col - corners
					tiles[i][j].setNum(tiles[i-1][j]);
					tiles[i][j].setNum(tiles[i+1][j]);
					tiles[i][j].setNum(tiles[i][j+1]);
					tiles[i][j].setNum(tiles[i-1][j+1]);
					tiles[i][j].setNum(tiles[i+1][j+1]);
				}else if(i == Value.I-1) {
					if(j == 0) { //bottom left
						tiles[i][j].setNum(tiles[i-1][j]);
						tiles[i][j].setNum(tiles[i][j+1]);
						tiles[i][j].setNum(tiles[i-1][j+1]);
					}else if(j == Value.J - 1) { //bottom right
						tiles[i][j].setNum(tiles[i-1][j]);
						tiles[i][j].setNum(tiles[i][j-1]);
						tiles[i][j].setNum(tiles[i-1][j-1]);
					}else { //bottom row
						tiles[i][j].setNum(tiles[i][j-1]);
						tiles[i][j].setNum(tiles[i][j+1]);
						tiles[i][j].setNum(tiles[i-1][j]);
						tiles[i][j].setNum(tiles[i-1][j-1]);
						tiles[i][j].setNum(tiles[i-1][j+1]);
					}
				}else if(j == Value.J - 1 && i < Value.I - 1 && i > 0) {
					tiles[i][j].setNum(tiles[i-1][j]);
					tiles[i][j].setNum(tiles[i+1][j]);
					tiles[i][j].setNum(tiles[i][j-1]);
					tiles[i][j].setNum(tiles[i-1][j-1]);
					tiles[i][j].setNum(tiles[i+1][j-1]);
				}else { //middle stuff
					tiles[i][j].setNum(tiles[i-1][j]);
					tiles[i][j].setNum(tiles[i][j-1]);
					tiles[i][j].setNum(tiles[i+1][j]);
					tiles[i][j].setNum(tiles[i][j+1]);
					tiles[i][j].setNum(tiles[i-1][j-1]);
					tiles[i][j].setNum(tiles[i-1][j+1]);
					tiles[i][j].setNum(tiles[i+1][j+1]);
					tiles[i][j].setNum(tiles[i+1][j-1]);
				}
				panel.add(tiles[i][j]);
			}
		}
		add(panel);	
	}
}
