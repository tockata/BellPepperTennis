import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Line2D;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Game extends JPanel {

	Ball ball = new Ball(this);
	Player1 player1 = new Player1(this);
	Player2 player2 = new Player2(this);

	public Game() {
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				player1.keyReleased(e);
				player2.keyReleased(e);

			}

			@Override
			public void keyPressed(KeyEvent e) {
				player1.keyPressed(e);
				player2.keyPressed(e);

			}
		});
		setFocusable(true);
	}

	private void move() {
		ball.move();
		player1.move();
		player2.move();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		ImageIcon cook = new ImageIcon(Game.class.getResource("resources/cook.png"));
		ImageIcon chef = new ImageIcon(Game.class.getResource("resources/chef.png"));
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setStroke(new BasicStroke(10));
		g2d.setColor(Color.WHITE);
		g2d.draw(new Line2D.Float(0, 70, 1200, 70));
		g.setFont(new Font("TimesRoman", Font.ITALIC, 24));
		g2d.drawString("Cook Student", 400, 40);
		String pl1Score = Integer.toString(player1.getPl1Score());
		g2d.drawString(pl1Score, 570, 40);
		String pl2Score = Integer.toString(player2.getPl2Score());
		g2d.drawString(pl2Score, 615, 40);
		g2d.drawString(":", 597, 40);
		g2d.drawString("Chef Nakov", 655, 40);
		player1.paint(g2d);
		player2.paint(g2d);
		ball.paint(g2d);
		g2d.drawImage(cook.getImage(), 340, 10, 50, 42, null);
		g2d.drawImage(chef.getImage(), 790, 7, 53, 50, null);
	}

	public void pointMade() {
		int dialogResult = JOptionPane.showConfirmDialog(this, "Ooops the pepper is out of the pan! \n"
				+ " Do you want to continue?", "Scooore!", JOptionPane.OK_CANCEL_OPTION);
		if (dialogResult == JOptionPane.OK_OPTION) {
			ball = new Ball(this);
			repaint();
		} else {
			System.exit(ABORT);
		}
	}

	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame("Bell Pepper Tennis");
		Game game = new Game();
		frame.add(game);
		frame.setSize(1200, 700);
		game.setBackground(new Color(31, 107, 2));
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		startOrExitGame(game);

		while (true) {
			game.move();
			game.repaint();
			Thread.sleep(5);
		}

	}

	private static void startOrExitGame(Game game) {
		int dialogResult = JOptionPane.showConfirmDialog(game, "Are you ready to play? \n"
				+ "Press \"OK\" to start the game or \"Cancel\" to exit", "Bell Pepper Tennis", JOptionPane.OK_CANCEL_OPTION);
		if (dialogResult == JOptionPane.OK_OPTION) {
			game.repaint();
		} else {
			System.exit(ABORT);
		}
	}

}
