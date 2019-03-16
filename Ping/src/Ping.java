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
	
	public static void init() {
		gameObject ball = new Ball(2,2,20,20,Color.WHITE,4);
		objects.add(ball);
		
		gameObject paddle = new Paddle(5,5,10,50,Color.WHITE,5);
		objects.add(paddle);
		
		gameObject paddle2 = new Paddle2(478,5,10,50,Color.WHITE,5);
		objects.add(paddle2);
		
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