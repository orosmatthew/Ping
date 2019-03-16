import java.awt.Color;
import java.awt.Graphics;

public class Ball extends gameObject {

	private double posX;
	private double posY;
	private int sizeX;
	private int sizeY;
	private Color color;
	private double speed = 3;
	private double[] velocity = {1,1};
	
	public Ball(double x, double y, int sx, int sy, Color c, double s) {
		posX = x;
		posY = y;
		sizeX = sx;
		sizeY = sy;
		color = c;
		speed = s;
	}
	
	
	public void paintObject(Graphics g) {
    	g.setColor(color);
        g.drawOval((int)posX,(int)posY,sizeX,sizeY);
        g.setColor(color);
        g.fillOval((int)posX,(int)posY,sizeX,sizeY);
		
	}

	public void process(double delta) {
		//Normalize velocity vector at certain speed
		double angle = Math.atan2(velocity[1], velocity[0]);
		velocity[0] = speed*Math.cos(angle);
		velocity[1] = speed*Math.sin(angle);
		
		//Wall Collision
		//Added randomness to wall collisions
		if(posX>=471) {
			velocity[0]*=-1;
			velocity[0]+=((Math.random()-0.5)*0.2);
		}
		if(posX<=0) {
			velocity[0]*=-1;
			velocity[0]+=((Math.random()-0.5)*0.2);
		}
		if(posY>=448) {
			velocity[1]*=-1;
			velocity[1]+=((Math.random()-0.5)*0.2);
		}
		if(posY<=0) {
			velocity[1]*=-1;
			velocity[1]+=((Math.random()-0.5)*0.2);
		}
		
		//Apply Motion
		posX+=(velocity[0]*delta);
		posY+=(velocity[1]*delta);
		
	}
}
