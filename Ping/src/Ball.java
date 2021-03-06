import java.awt.Color;
import java.awt.Graphics;

public class Ball extends gameObject {

	private double posX;
	private double posY;
	private int sizeX;
	private int sizeY;
	private Color color;
	private double speed = 3;
	private double[] velocity = {1,0};
	private Paddle paddleLeft;
	private Paddle paddleRight;
	
	public Ball(double x, double y, int sx, int sy, Color c, double s) {
		posX = x-(sx*0.5);
		posY = y-(sy*0.5);
		sizeX = sx;
		sizeY = sy;
		color = c;
		speed = s;
	}
	
	public double getPosX() { return posX; }
	public double getPosY() { return posY; }
	public int getSizeX() { return sizeX; }
	public int getSizeY() { return sizeY; }
	
	public void setPosX(double x) {
		posX = x;
	}
	
	public void setPosY(double y) {
		posY = y;
	}
	
	public void setPaddleLeft(Paddle p) {
		paddleLeft = p;
	}
	public void setPaddleRight(Paddle p) {
		paddleRight = p;
	}
	
	public void setVelocity(int[] v) {
		velocity[0] = v[0];
		velocity[1] = v[1];
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
		//Paddle Collision
		if (paddleLeft!=null) {
			if(posY>=(paddleLeft.getPosY()-sizeY) && posY<=(paddleLeft.getPosY()+paddleLeft.getSizeY())) {
				if (posX>=paddleLeft.getPosX() && posX<=(paddleLeft.getPosX()+paddleLeft.getSizeX())) {
					if (velocity[0]<0) {
						velocity[0]*=-1;
						velocity[0]+=((Math.random()-0.5)*5);
						velocity[1]+=((Math.random()-0.5)*2);
					}
					velocity[1]-=(paddleLeft.getCurrentSpeed()*0.3);

				}
			}
		}
		if (paddleRight!=null) {
			if(posY>=(paddleRight.getPosY()-sizeY) && posY<=(paddleRight.getPosY()+paddleRight.getSizeY())) {
				if (posX<=paddleRight.getPosX() && posX>=(paddleRight.getPosX()-sizeX)) {
					if (velocity[0]>0) {
						velocity[0]*=-1;
						velocity[0]+=((Math.random()-0.5)*5);
						velocity[1]+=((Math.random()-0.5)*2);
					}
					velocity[1]-=(paddleRight.getCurrentSpeed()*0.3);

				}
			}
		}
		
		//Wall Collision
		//Added randomness to wall collisions
		if(posX>=(491-sizeX)) {
			if (velocity[0]>0) {
				velocity[0]*=-1;
			}
			velocity[0]+=((Math.random()-0.5)*4);
			velocity[1]+=((Math.random()-0.5)*1.5);
			Ping.win(0);
		}
		if(posX<=0) {
			if (velocity[0]<0) {
				velocity[0]*=-1;
			}
			velocity[0]+=((Math.random()-0.5)*4);
			velocity[1]+=((Math.random()-0.5)*1.5);
			Ping.win(1);
		}
		if(posY>=(468-sizeY)) {
			if (velocity[1]>0) {
				velocity[1]*=-1;
			}
			velocity[0]+=((Math.random()-0.5)*4);
			velocity[1]+=((Math.random()-0.5)*1.5);
		}
		if(posY<=0) {
			if (velocity[1]<0) {
				velocity[1]*=-1;
			}
			velocity[0]+=((Math.random()-0.5)*4);
			velocity[1]+=((Math.random()-0.5)*1.5);
		}
		
		//Apply Motion
		posX+=(velocity[0]*delta);
		posY+=(velocity[1]*delta);
		
	}
}
