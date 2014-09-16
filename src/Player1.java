import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player1 {
	private static final int WIDTH = 100;
	private static final int HEIGHT = 135;
	private static final int X = 15;

	private Image tennisRacket;
	int y = 310;
	int ya = 0;
	int player1Score = 0;
	
	private Game game;

	public Player1(Game game) {
		this.game = game;
	}

	public void move() {
		if (y + ya > 72 && y + ya < game.getHeight() - 135) {
			y = y + ya;
		}
	}

	public void paint(Graphics2D g) {
		ImageIcon racketUp = new ImageIcon(Player1.class.getResource("resources/racket_up.png"));
		ImageIcon racketDown = new ImageIcon(Player1.class.getResource("resources/racket_down.png"));
		if (y < 310) {
			tennisRacket = racketUp.getImage();
		} else {
			tennisRacket = racketDown.getImage();
		}
		g.drawImage(tennisRacket, X, y, WIDTH, HEIGHT, null);
	}

	public void keyReleased(KeyEvent e) {
		ya = 0;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_S) {
			ya = 3;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			ya = -3;
		}
	}

	public Rectangle getPlayer1Borders() {
		if (y < 310) {
		return new Rectangle(X - 50, y, WIDTH, HEIGHT - 50);
		} else {
			return new Rectangle(X - 50, y + 50, WIDTH, HEIGHT);
		}
	}
	
	public int getWidth() {
		return WIDTH;
	}
	
	public int getPl1Score() {
		return player1Score;
	}
	
	public void setpl1Score(int player1Score) {
		this.player1Score = player1Score;
	}
}
