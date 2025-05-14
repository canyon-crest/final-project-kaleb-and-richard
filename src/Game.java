import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game {
	public static final int WIDTH = 450;
	public static final int HEIGHT = 600;
	
	private JFrame frame;
	private GamePanel panel;
	private Timer timer;
	
	public static void main(String[] args) {
		new Game().run();
	}
	
	public void run()
	{
		frame = new JFrame();
		panel = new GamePanel();
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(WIDTH, HEIGHT);
		
		timer = new Timer(1000/24, (e) -> update());
		
		startGame();
	}
	
	private void update() {
		// TODO Auto-generated method stub
		panel.repaint();
	}

	public void startGame()
	{
		panel.repaint();
		timer.start();
	}
}
