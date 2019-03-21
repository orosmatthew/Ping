import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Ping extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	private static JFrame window = new JFrame("Ping");
	private static Draw mainDraw = new Draw();
	
	private static ArrayList<gameObject> objects = new ArrayList<>();
	private static ArrayList<Integer> keysPressed = new ArrayList<>();
	private static int[] wins = {0,0};
	
	private static Ball mainBall;
	private static Paddle leftPaddle;
	private static Paddle rightPaddle;
	
	public static void init() {
		gameObject ball = new Ball(240,230,20,20,Color.WHITE,9);
		objects.add(ball);
		mainBall = (Ball) ball;
		
		gameObject paddle = new Paddle(5,200,10,80,Color.WHITE,9);
		objects.add(paddle);
		((Ball)ball).setPaddleLeft((Paddle)paddle);
		leftPaddle = (Paddle) paddle;
		
		gameObject paddle2 = new Paddle2(478,200,10,80,Color.WHITE,9);
		objects.add(paddle2);
		((Ball)ball).setPaddleRight((Paddle)paddle2);
		rightPaddle = (Paddle) paddle2;
		
	}
	
	public static void main(String[] args) {
		createWindow();
		SwingUtilities.updateComponentTreeUI(window);
		window.getContentPane().add(mainDraw);
		window.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent e) {}
			public void keyPressed(KeyEvent e) {
				if(!keysPressed.contains(e.getKeyCode())) {
					keysPressed.add(e.getKeyCode());
				}
			}
			
			public void keyReleased(KeyEvent e) {
				if(keysPressed.contains(e.getKeyCode())) {
					keysPressed.remove((Integer)(e.getKeyCode()));
				}
			}
		});
		init();
		gameLoop();
	}
	
	public static void win(int n) {
		wins[n] += 1;
		mainBall.setPosX(240);
		mainBall.setPosY(230);
		leftPaddle.setPosX(5);
		leftPaddle.setPosY(200);
		rightPaddle.setPosX(478);
		rightPaddle.setPosY(200);
		int[] v = {0,0};
		if (n==1) {
			v[0] = 1;
		}else {
			v[0] = -1;
		}
		mainBall.setVelocity(v);
	}
	
	public static boolean isKeyPressed(int code) {
		if (keysPressed.contains((Integer)(code)))
			return true;
		else
			return false;

	}
	public static void gameLoop() {
	    long lastTime = System.nanoTime();
	    final double TARGET_FPS = 60.0;
	    final double OPTIMAL_TIME = 1000000000 / TARGET_FPS;
	    double delta = 0;
	    while (true) {
		    long now = System.nanoTime();
		    long updateLength = now - lastTime;
		    delta += (updateLength/((double)OPTIMAL_TIME));
		    lastTime = now;
		    while(delta >= 1) {
		    	
	           	for (gameObject o : objects) {
	           		o.process(((double)Math.round(delta * 100d) / 100d));
	           	}
	           	process(((double)Math.round(delta * 100d) / 100d));
	           	delta=0;
		    }
	    }
	}
	
	
	public static void process(double delta) {
		redraw();
	}
	
	static class Draw extends JComponent{
		private static final long serialVersionUID = 1L;

		public void paint(Graphics g){
            for(gameObject o : objects) {
            	o.paintObject(g);
            }
            g.setFont(new Font("Arial", Font.PLAIN, 36)); 
            g.drawString(wins[0]+"", 140, 40);
            g.drawString(wins[1]+"", 340, 40);
        }
    }
	
	public static void redraw() {
		mainDraw.paintImmediately(0, 0, 500, 500);
	}
	
    public static void createWindow() {
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(500,500);
        window.setResizable(false);
        window.setVisible(true);
        window.getContentPane().setBackground(Color.BLACK);
    }
}