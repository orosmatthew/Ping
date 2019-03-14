import java.awt.Color;
import java.awt.Graphics;

import com.sun.glass.events.KeyEvent;

public class Ball extends gameObject {

	private int posX;
	private int posY;
	private int sizeX;
	private int sizeY;
	private Color color;
	private double speed;
	//private boolean toggle;
	
	public Ball(int x, int y, int sx, int sy, Color c, double s) {
		posX = x;
		posY = y;
		sizeX = sx;
		sizeY = sy;
		color = c;
		speed = s;
		//toggle = false;
	}
	
	
	public void paintObject(Graphics g) {
    	g.setColor(color);
        g.drawOval(posX,posY,sizeX,sizeY);
        g.setColor(color);
        g.fillOval(posX,posY,sizeX,sizeY);
		
	}

	public void process(double delta) {
		if (Ping.isKeyPressed(KeyEvent.VK_UP) || Ping.isKeyPressed(KeyEvent.VK_W)) {
			posY-=(speed*delta);
		}
		if (Ping.isKeyPressed(KeyEvent.VK_DOWN) || Ping.isKeyPressed(KeyEvent.VK_S)) {
			posY+=(speed*delta);
		}
		if (Ping.isKeyPressed(KeyEvent.VK_LEFT) || Ping.isKeyPressed(KeyEvent.VK_A)) {
			posX-=(speed*delta);
		}
		if (Ping.isKeyPressed(KeyEvent.VK_RIGHT) || Ping.isKeyPressed(KeyEvent.VK_D)) {
			posX+=(speed*delta);
		}
		/*
		if(posX>375 && toggle == false)
			toggle = true;
		else if(posX<0 && toggle == true)
			toggle = false;
        if(toggle == false){
        	posX+=(speed*delta);
        	posY+=(speed*delta);
        }
        if(toggle == true){
        	posX-=(speed*delta);
        	posY-=(speed*delta);
        }*/
	}
}
