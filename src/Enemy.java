import java.awt.Color;
import java.awt.Graphics;
import java.util.Timer;

public class Enemy extends Entity {
	private boolean alive;
	private int laneChangeCdMax;
	private int laneChangeCd;
	
	public Enemy(int speed, int cd) {
		super((int)(Math.random()*3),0, speed);
		laneChangeCdMax = cd;
		laneChangeCd = (int)(Math.random() * laneChangeCdMax * 0.5);
		alive = true;
	}
	
	public void removeHealth(Player player) {
		player.removeHealth(1);
	}
	
	public void die() {
		alive = false;
	}
	
	public void changeLane(Player p) {
		if (Math.abs(getY() - p.getY()) < 150)
			return;
		int lane = getLane();
		if (lane == 0)
		{
			lane += 1;
		}
		else if (lane == 2)
		{
			lane -= 1;
		}
		else
		{
			if (Math.random() < 0.5)
				lane -= 1;
			else
				lane += 1;
		}
		super.setLane(lane);
	}
	
	
	
	
	public void update(Player p)
	{
		if(alive)
		{
			if (laneChangeCd >= laneChangeCdMax)
			{
				laneChangeCd = (int)(Math.random() * laneChangeCdMax * 0.5);;
				changeLane(p);
			}
			else
			{
				laneChangeCd += 1000/Game.FPS;
			}
		}
		super.update(p);
		if (isTouching(p) && alive)
		{
			//player loses a life
			//enemy is destroyed
		}
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.RED);
    	g.fillRect(150 * getLane() + 50, getY() - 50, 50, 100);
	}
}
