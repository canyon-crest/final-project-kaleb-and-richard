import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game implements KeyListener {
	public static final int WIDTH = 450;
	public static final int HEIGHT = 600;
	public static final int FPS = 60;
	
	private JFrame frame;
	private GamePanel panel;
	private Timer timer;
	
	private Player player;
	private ArrayList<Entity> entities;
	
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
		frame.addKeyListener(this);
		
		timer = new Timer(1000/FPS, (e) -> update());
		
		startGame();
	}
	
	private void update() {
		// TODO Auto-generated method stub
		for (Entity e : entities)
		{
			e.move(player);
		}
		panel.repaint(player, entities);
	}

	public void startGame()
	{
		String name = JOptionPane.showInputDialog("Input a name:");
		player = new Player(1, HEIGHT - 100, 1, 10, name);
		entities = new ArrayList<Entity>();
		entities.add(new Enemy(5, 3, 1.0));
		update();
		timer.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 68 && player.getLane() < 2)
			player.setLane(player.getLane() + 1);
		if (e.getKeyCode() == 65 && player.getLane() > 0)
			player.setLane(player.getLane() - 1);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
