import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Paddle extends gameObject{
	protected Color color;
	protected double posX;
	protected double posY;
	protected int sizeX;
	protected int sizeY;
	protected double speed;
	protected double currentSpeed;
	
	public Paddle() {}
	
	public Paddle(double x, double y, int sx, int sy, Color c, double s) {
		posX = x;
		posY = y;
		sizeX = sx;
		sizeY = sy;
		color = c;
		speed = s;
	}
	
	public void paintObject(Graphics g) {
    	g.setColor(color);
        g.fillRect((int)posX,(int)posY,sizeX,sizeY);
        g.setColor(color);
        g.drawRect((int)posX,(int)posY,sizeX,sizeY);
		
	}
	
	public double getPosX() { return posX; }
	public double getPosY() { return posY; }
	public int getSizeX() { return sizeX; }
	public int getSizeY() { return sizeY; }
	public double getCurrentSpeed() { return currentSpeed; }
	
	public void process(double delta) {
		currentSpeed = 0;
		if (Ping.isKeyPressed(KeyEvent.VK_W)) {
			if(posY>=10) {
				currentSpeed = speed;
				posY-=(speed*delta);
			}
		}
		if (Ping.isKeyPressed(KeyEvent.VK_S)) {
			if(posY<=411) {
				currentSpeed = -speed;
				posY+=(speed*delta);
			}
		}

	}
}
