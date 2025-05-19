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
	public static final int FPS = 30;
	
	private JFrame frame;
	private GamePanel panel;
	private Timer timer;
	
	private Player player;
	private ArrayList<Entity> entities;
	
	private int spawnEnemyCd;
	private int spawnEnemyCdMax;
	private double spawnEnemyChance;
	
	private int spawnPowerupCd;
	private int spawnPowerupCdMax;
	private double spawnPowerupChance;
	
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
		if (spawnEnemyCd >= spawnEnemyCdMax)
		{
			if (Math.random() < spawnEnemyChance)
			{
				spawnEnemyCd = 0;
				spawnEnemy();
			}
		}
		else
		{
			spawnEnemyCd += 1000/Game.FPS;
		}
		if (spawnPowerupCd >= spawnPowerupCdMax)
		{
			if (Math.random() < spawnPowerupChance)
			{
				spawnPowerupCd = 0;
				spawnPowerup();
			}
		}
		else
		{
			spawnPowerupCd += 1000/Game.FPS;
		}
		
		for (Entity e : entities)
		{
			e.update(player);
		}
		panel.update(panel.getGraphics(), player, entities);
	}

	public void startGame()
	{
		spawnEnemyCd = 0;
		spawnEnemyCdMax = 2000;
		spawnEnemyChance = 0.5;

		spawnPowerupCd = 0;
		spawnPowerupCdMax = 5000;
		spawnPowerupChance = 0.1;
		
		String name = JOptionPane.showInputDialog("Input a name:");
		player = new Player(1, HEIGHT - 100, 1, 30, name);
		entities = new ArrayList<Entity>();
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
	
	
	
	public void spawnEnemy()
	{
		entities.add(new Enemy((int)(player.getSpeed() * 0.75), 1000, 0.5));
	}
	public void spawnPowerup()
	{
		entities.add(new Powerup("placeholder", "placeholder"));
	}
}
