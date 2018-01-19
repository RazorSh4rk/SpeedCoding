import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tile extends JPanel implements MouseListener{

	private JLabel text;
	private boolean isBomb;
	public int num;
	
	public Tile() {
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		text = new JLabel("x");
		num = 0;
		add(text);
		addMouseListener(this);
		randomize();
	}
	
	public void setText(String s) {
		text.setText(s);
	}
	private void randomize() {
		if(new Random().nextInt(10) == 0)
			setBomb(true);
	}
	public void setNum(Tile t) {
		if(t.isBomb()) 
			num++;
	}
	public boolean isBomb() {
		return isBomb;
	}
	public void setBomb(boolean isBomb) {
		this.isBomb = isBomb;
	}
	private boolean check() {
		if(isBomb) {
			setBackground(Color.RED);
			setText("boom");
		}else {
			setBackground(Color.GREEN);
			setText(num + "");
		}
		show();
		return isBomb;
	}
	public void show() {
		text.setVisible(true);
	}
	public void hide() {
		text.setVisible(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(check())
			((JFrame)Tile.this.getTopLevelAncestor()).dispose();
	}
	
}
