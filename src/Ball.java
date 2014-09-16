import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Ball {
	private static final int DIAMETER = 50;
	int x = 575;
	int y = 290;
	int xa = randomDirection();
	int ya = randomDirection();
	int collisionCount = 0;
	int speed = 1;
	int timeCount = 0;
	private Image tennisBall;

	private Game game;

	public Ball(Game game) {
		this.game = game;
	}

	public void move() {
		if (x + xa < 0) {
			game.player2.setpl2Score(game.player2.getPl2Score() + 1);
			try {
				Sound.outOfBounds();
			} catch (Exception e) {
				e.printStackTrace();
			}
			game.pointMade();
		}
		if (x + xa > game.getWidth() - DIAMETER) {
			game.player1.setpl1Score(game.player1.getPl1Score() + 1);
			try {
				Sound.outOfBounds();
			} catch (Exception e) {
				e.printStackTrace();
			}
			game.pointMade();
		}
		if (y + ya < 70) {
			ya = speed;
			try {
				Sound.tableHit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (y + ya > game.getHeight() - DIAMETER) {
			ya = - speed;
			try {
				Sound.tableHit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (collisionPlayer1()) {
			xa = speed;
			ya = ya * randomDirection();
			collisionCount++;
			try {
				Sound.player1Hit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (collisionPlayer2()) {
			xa = - speed;
			ya = ya * randomDirection();
			collisionCount++;
			try {
				Sound.player2Hit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		x += xa;
		y += ya;
		switch (collisionCount) {
		case 6: speed = 2; break;
		case 12: speed = 3; break;
		case 18: speed = 4; break;
		default: break;
		}
	}

	public void paint(Graphics2D g) {
		ImageIcon tennisBallFace = new ImageIcon(Ball.class.getResource("resources/tennis_ball.png"));
		ImageIcon tennisBallLeft = new ImageIcon(Ball.class.getResource("resources/tennis_ball_left.png"));
		ImageIcon tennisBallRight = new ImageIcon(Ball.class.getResource("resources/tennis_ball_right.png"));
		ImageIcon tennisBallBack = new ImageIcon(Ball.class.getResource("resources/tennis_ball_blank.png"));
		timeCount++;
		 if (timeCount >= 0 && timeCount <= 50) {
		     tennisBall = tennisBallLeft.getImage();
		 } else if (timeCount > 50 && timeCount <= 100) {
		      tennisBall = tennisBallFace.getImage();
		 } else if (timeCount > 100 && timeCount <= 150) {
		     tennisBall = tennisBallRight.getImage();
		 } else if (timeCount > 150 && timeCount < 200) {
		      tennisBall = tennisBallBack.getImage();
		 } else {
		     timeCount = 0;
		 }
		g.drawImage(tennisBall, x, y, DIAMETER, DIAMETER, null);
	}

	public Rectangle getBallBorders() {
		return new Rectangle(x, y, DIAMETER, DIAMETER);
	}

	private boolean collisionPlayer1() {
		return game.player1.getPlayer1Borders().intersects(getBallBorders());
	}
	
	private boolean collisionPlayer2() {
		return game.player2.getPlayer2Borders().intersects(getBallBorders());
	}
	
	private int randomDirection(){
		Random rnd = new Random();
		return rnd.nextInt(2) * 2 - 1;
		
	}

}
