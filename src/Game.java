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
	
	private int score;
	private int scoreCd;
	
	public static void main(String[] args) {
		new Game().run();
	}
	
	public void run()
	{
		frame = new JFrame();
		panel = new GamePanel(this);
		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(WIDTH, HEIGHT);
		frame.addKeyListener(this);
		
		timer = new Timer(1000/FPS, (e) -> update());
		
		startGame();
	}

	public void startGame()
	{
		spawnEnemyCd = 0;
		spawnEnemyCdMax = 2000;
		spawnEnemyChance = 0.8;

		spawnPowerupCd = 0;
		spawnPowerupCdMax = 5000;
		spawnPowerupChance = 0.25;
		
		String name = JOptionPane.showInputDialog("Input a name:");
		player = new Player(this, 1, HEIGHT - 100, 1, 10, name);
		player.changeAccelRate(10000);
		player.changeMaxSpeed(30);
		entities = new ArrayList<Entity>();
		update();
		timer.start();
	}
	
	private void update() {
		// TODO Auto-generated method stub
		spawnEnemyCdMax = (int) (2000 / (player.getSpeed() / 10.0));
		score();
		
		if (spawnEnemyCd >= spawnEnemyCdMax)
		{
			spawnEnemyCd = 0;
			if (Math.random() < spawnEnemyChance)
			{
				spawnEnemy();
			}
		}
		else
		{
			spawnEnemyCd += 1000/Game.FPS;
		}
		if (spawnPowerupCd >= spawnPowerupCdMax)
		{
			spawnPowerupCd = 0;
			if (Math.random() < spawnPowerupChance)
			{
				spawnPowerup();
			}
		}
		else
		{
			spawnPowerupCd += 1000/Game.FPS;
		}
		
		for (int i = entities.size() - 1; i >= 0; i--)
		{
			if (entities.get(i).isDeleted())
				entities.remove(i);
			else
				entities.get(i).update();
		}
		player.update();
		
		panel.update(panel.getGraphics());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyChar() == ' ') {
			for (Powerup p : player.getPowerups()) {
				if (p instanceof Gun) {
					((Gun) p).shoot();
					break;
				}
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 68 && player.getLane() < 2)
			player.setLane(player.getLane() + 1);
		if (e.getKeyCode() == 65 && player.getLane() > 0)
			player.setLane(player.getLane() - 1);
		if (e.getKeyCode() == 0)
			for (Powerup i : player.getPowerups())
				if (i instanceof Gun)
					((Gun)i).shoot();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public void spawnEnemy()
	{
		addEntity(new Enemy(this, (int)(player.getSpeed() * 0.75), 10000/player.getSpeed(), 0.5));
	}
	public void spawnPowerup()
	{
		int rand = (int)(Math.random() * 3);
		if (rand == 0)
			addEntity(new ExtraLife(this, "life", "adds health"));
		else if (rand == 1)
			addEntity(new SlowDown(this, "slow", "decreases speed"));
		else if (rand == 2)
			addEntity(new Gun(this, 3, "gun", "press space to shoot"));
	}
	
	public Player getPlayer()
	{
		return player;
	}
	public ArrayList<Entity> getEntities()
	{
		return entities;
	}
	
	public void addEntity(Entity e)
	{
		entities.add(0, e);
	}
	
	public void score()
	{
		if (scoreCd > 1000)
		{
			scoreCd = 0;
			score++;
		}
		else
			scoreCd += 1000/FPS;
	}
	public int getScore()
	{
		return score;
	}
}
