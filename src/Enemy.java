import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;

public class Enemy extends Entity {
	boolean alive = true;
	private int laneChangeCooldown;
	private int laneChangeCount;
	private double laneChangeChance;
	
	public Enemy(int speed, int cd, double chance) {
		super((int)(Math.random()*3),0, speed);
		laneChangeCooldown = cd;
		laneChangeCount = 0;
		laneChangeChance = chance;
	}
	
	public void removeHealth(Player player) {
		player.removeHealth(1);
	}
	
	public void die() {
		alive = false;
	}
	
	public void changeLane(Player p) {
		while (p.getHealth() > 0) {
			try {
				Thread.sleep((long) (Math.random()*2+1));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (alive) {
				super.setLane((int)(Math.random()*3));
			}
		}
	}
	
	
	
	public void draw(Graphics g)
	{
		g.setColor(Color.RED);
    	g.fillRect(150 * getLane() + 50, getY() - 50, 50, 100);
	}
}
