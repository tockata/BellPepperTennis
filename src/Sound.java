import java.net.URL;
import javax.sound.sampled.*;

public class Sound {

    public static void main(String[] args) {
    }

	public static void player1Hit() throws Exception {
		URL player1Hit = new Sound().getClass().getResource("player1hit.wav");
		playSelected(player1Hit);
	}
	
	public static void player2Hit() throws Exception {
		URL player2Hit = new Sound().getClass().getResource("player2hit.wav");
		playSelected(player2Hit);
	}
	
	public static void outOfBounds() throws Exception {
		URL outOfBounds = new Sound().getClass().getResource("out.wav");
		playSelected(outOfBounds);
	}
	
	public static void tableHit() throws Exception {
		URL tableHit = new Sound().getClass().getResource("tablehit.wav");
		playSelected(tableHit);
	}

	private static void playSelected(URL player) throws Exception {
		Clip clip = AudioSystem.getClip();
		AudioInputStream ais = AudioSystem.getAudioInputStream( player );
	    clip.open(ais);
	    clip.start();
	}
}
