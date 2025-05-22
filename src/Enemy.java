import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;

import javax.imageio.ImageIO;

public class Enemy extends Entity {
	private BufferedImage image;
	private boolean alive;
	private int laneChangeCdMax;
	private int laneChangeCd;
	private double laneChangeChance;
	
	/*
	 * @param cd: cooldown, ie how often enemy cars change lanes
	 * @param chance: probability that a car will change lanes after cooldown
	 */
	public Enemy(Game game, int speed, int cd, double chance) {
		super(game, (int)(Math.random()*3),0, speed);
    	try {
			image = ImageIO.read(new File("images/enemy_car.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		laneChangeCdMax = cd;
		laneChangeCd = (int)(Math.random() * laneChangeCdMax);
		laneChangeChance = chance;
		alive = true;
	}
	
	public void removeHealth(Player player) { // removes 1hp from player after crashing
		player.removeHealth(1);
	}
	
	public void die() {
		alive = false;
	}
	
	public void changeLane(Player p) {
		if (alive) { // if enemy is dead (aka been shot by player) the car can no longer change lanes
			if (Math.abs(getY() - p.getY()) < 150) // only change lanes if the enemy has not crashed into the player
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
	}
	
	
	
	@Override
	public void update()
	{
		Player p = getGame().getPlayer();
		if(alive)
		{
			if (laneChangeCd >= laneChangeCdMax)
			{
				laneChangeCd = (int)(Math.random() * laneChangeCdMax * 0.5);
				if (Math.random() < laneChangeChance)
				{
					changeLane(p);
				}
			}
			else
			{
				laneChangeCd += 1000/Game.FPS;
			}
		}
		super.update();
		if (isTouching(p) && alive)
		{
			//player loses a life
			//enemy is destroyed
			p.removeHealth(1);
			changeAccelRate(-1);
			
			die();
		}
	}
	
	public void draw(Graphics g)
	{
//		g.setColor(Color.RED);
//    	g.fillRect(150 * getLane() + 50, getY() - 50, 50, 100);
    	g.drawImage(image, 150 * getLane() + 75 - 78, getY() - 75, 150, 150, null);
	}
}
