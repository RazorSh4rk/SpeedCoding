import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

public class Game extends JFrame implements KeyListener, Runnable{

	private List<Block> blocks;
	private Player player;
	private int spawnTimer, spawnConst, difficulty, playerSpeed;
	Random r;
	
	int maxSpawnX = StaticContainer.GAME_W - StaticContainer.BLOCK_W;
	
	 public Game() {
		init();
	}
	private void init() {
		setTitle("Car thing");
		getContentPane().setLayout(null);
		setSize(StaticContainer.GAME_W, StaticContainer.GAME_H);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setBackground(Color.BLACK);
		addKeyListener(this);
		
		r = new Random();
		spawnTimer = 0;
		difficulty = 5;
		playerSpeed = 10;
		spawnConst = 40;
		blocks = new ArrayList<>();
		player = new Player();
		blocks.add(new Block(r.nextInt(maxSpawnX), 0));
		drawFrame();
	}
	private void drawFrame() {
		getContentPane().removeAll();
		for(Block b : blocks) {
			b.move(difficulty);
			b.setLocation(b.x, b.y);
			getContentPane().add(b);
		}
		player.setLocation(player.x, player.y);
		getContentPane().add(player);
		
		getContentPane().repaint();
		getContentPane().revalidate();
	}
	private void spawn(int i) {
		if(i == spawnConst)
			blocks.add(new Block(r.nextInt(StaticContainer.GAME_W - StaticContainer.BLOCK_W), 0));
	}
	private boolean checkCollision() {
		for(Block b : blocks) {
			if(b.y + StaticContainer.BLOCK_H >= player.y &&
				b.y <= player.y + StaticContainer.PLAYER_H &&
				b.x >= player.x - StaticContainer.PLAYER_W &&
				b.x <= player.x + StaticContainer.PLAYER_W) {
					return true;
			}
		}
		return false;
	}
	
	//**********IMPLEMENTED	
	@Override
	public void run() {
		while(true) {
			drawFrame();
			spawn(spawnTimer);
			if(++spawnTimer > spawnConst) spawnTimer = 0;
			if(checkCollision()) this.dispose();
			try {
				Thread.sleep(30);
			}catch (InterruptedException ie) {
				ie.printStackTrace();
				this.dispose();
			}
		}
	}	

	@Override
	public void keyPressed(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			if(player.x - playerSpeed >= 0)
				player.x -= 10;
			break;
		case KeyEvent.VK_D:
			if(player.x + playerSpeed <= StaticContainer.GAME_W - playerSpeed)
				player.x += playerSpeed;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

}
