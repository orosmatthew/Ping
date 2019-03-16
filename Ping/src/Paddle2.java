import java.awt.Color;
import java.awt.event.KeyEvent;

public class Paddle2 extends Paddle {

	public Paddle2(double x, double y, int sx, int sy, Color c, double s) {
		super(x, y, sx, sy, c, s);
	}
	
	public void process(double delta) {
		if (Ping.isKeyPressed(KeyEvent.VK_UP)) {
			if(posY>=10) {
				posY-=(speed*delta);
			}
		}
		if (Ping.isKeyPressed(KeyEvent.VK_DOWN)) {
			if(posY<=411) {
				posY+=(speed*delta);
			}
		}
	}

}
