import java.awt.Color;

public class PaddleAI extends Paddle {
	
	public PaddleAI(double x, double y, int sx, int sy, Color c, double s) {
		super(x, y, sx, sy, c, s);
	}
	
	public void process(double delta) {
		currentSpeed = 0;
		if (Ping.mainBall.getPosY()<(posY+(sizeY*0.5))) {
			if(posY>=10) {
				currentSpeed = speed;
				posY-=(currentSpeed*delta);
			}
		}
		if (Ping.mainBall.getPosY()>(posY+(sizeY*0.5))) {
			if(posY<=411) {
				currentSpeed = -speed;
				posY-=(currentSpeed*delta);
			}
		}
	}

}
