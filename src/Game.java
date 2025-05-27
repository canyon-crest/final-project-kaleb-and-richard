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
	private Timer timer2;
	
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
	public static int highScore = 0;
	public static String highScoreHolder = "";
	
	public boolean gameOver;
	
	public static void main(String[] args) {
		new Game().run(); // runs the game
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
		score = 0;
		//initiate the variables/stats for the enemies
		spawnEnemyCd = 0;
		spawnEnemyCdMax = 2000;
		spawnEnemyChance = 0.8;
		
		//initiate the variables/stats for the powerups
		spawnPowerupCd = 0;
		spawnPowerupCdMax = 5000;
		spawnPowerupChance = 0.25;
		
		//initiate the variables/stats for the player
		String name = JOptionPane.showInputDialog("Input a name:");
		player = new Player(this, 1, HEIGHT - 100, 1, 10, name);
		player.changeAccelRate(10000);
		player.changeMaxSpeed(30);
		entities = new ArrayList<Entity>();
		update();
		timer.start();
		gameOver = false;
	}
	
	private void update() {
		// TODO Auto-generated method stub
		if (gameOver)
			return;
		
		spawnEnemyCdMax = (int) (2000 / (player.getSpeed() / 10.0));
		score();
		
		if (spawnEnemyCd >= spawnEnemyCdMax) //only spawn enemies after cooldown
		{
			spawnEnemyCd = 0;
			if (Math.random() < spawnEnemyChance)
			{
				spawnEnemy();
			}
		}
		else
		{
			spawnEnemyCd += 1000/Game.FPS; //if cooldown still active, wait (ie increment time)
		}
		if (spawnPowerupCd >= spawnPowerupCdMax) //only spawn powerups after cooldown
		{
			spawnPowerupCd = 0;
			if (Math.random() < spawnPowerupChance)
			{
				spawnPowerup();
			}
		}
		else
		{
			spawnPowerupCd += 1000/Game.FPS; //if cooldown still active, wait (ie increment time)
		}
		
		for (int i = entities.size() - 1; i >= 0; i--)
		{
			if (entities.get(i).isDeleted())
				entities.remove(i);
			else
				entities.get(i).update();
		}
		player.update();
		
		if (player.getHealth() <= 0)
		{
			gameOver();
		}
		panel.update(panel.getGraphics());
	}

	private void gameOver() {
		if (score > highScore)
		{
			highScore = score;
			highScoreHolder = player.getName();
		}
		gameOver = true;
		timer.stop();
	}

	@Override
	public void keyTyped(KeyEvent e) { // shoot gun when spacebar is pressed
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) { //code for the controls
		// TODO Auto-generated method stub
//		System.out.println(e.getKeyCode());
		if (e.getKeyCode() == 68 && player.getLane() < 2) // d moves car to the right
			player.setLane(player.getLane() + 1);
		if (e.getKeyCode() == 65 && player.getLane() > 0) // a moves car to the left
			player.setLane(player.getLane() - 1);
		if (e.getKeyCode() == 32) { //spacebar fires gun
			if (gameOver)
			{
				startGame();
			}
			else
			{
				player.shoot();
			}
		}
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
