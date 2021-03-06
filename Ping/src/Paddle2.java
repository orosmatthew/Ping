import java.awt.Color;
import java.awt.event.KeyEvent;

public class Paddle2 extends Paddle {

	public Paddle2(double x, double y, int sx, int sy, Color c, double s) {
		super(x, y, sx, sy, c, s);
	}
	
	public void process(double delta) {
		currentSpeed = 0;
		if (Ping.isKeyPressed(KeyEvent.VK_UP)) {
			if(posY>=10) {
				currentSpeed = speed;
				posY-=(currentSpeed*delta);
			}
		}
		if (Ping.isKeyPressed(KeyEvent.VK_DOWN)) {
			if(posY<=411) {
				currentSpeed = -speed;
				posY-=(currentSpeed*delta);
			}
		}
	}

}
