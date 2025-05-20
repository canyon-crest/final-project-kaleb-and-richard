import java.awt.Color;
import java.awt.Graphics;

public class Entity {
	private int lane;
	private int yPos;
	public static int maxSpeed;
	private boolean isDestroyed = false;
	public static int accelRate;
	private int speed; //meters per second
	
	public Entity(int startLane, int startY, int startSpeed) {
		lane = startLane;
		yPos = startY;
		speed = startSpeed;
	}
	
	public boolean isTouching(Entity other) {
		if (other.lane == this.lane && Math.abs(other.yPos - this.yPos) < 100) {
			return true;
		}
		else {
			return false;
		}
	}
	
	private void accelerate(Player p) { // increment speed by 5 every accelRate seconds
		while(p.getHealth() != 0) {
			try {
				Thread.sleep(accelRate*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			speed += 5;
		}
	}
	
	public static void changeMaxSpeed(int newLimit) { // change max speed based on difficulty level
		maxSpeed = newLimit;
	}
	
	public static void changeAccelRate(int newRate) {
		accelRate = newRate;
	}
	
    public int getSpeed() {
    	return speed;
    }
    
    public void setSpeed(int s) {
    	speed = s;
    }
	
	public void changeSpeed(int speed) {
		this.speed += speed;
	}
	
	public int getLane()
	{
		return lane;
	}
	public int getY()
	{
		return yPos;
	}
	
	public void setLane(int lane)
	{
		this.lane = lane;
	}
	public void setY(int y)
	{
		this.yPos = y;
	}
	
	public void move(Player p) { // increment yPos by speed every x amount of time
		yPos += (p.getSpeed() - getSpeed()) * (50.0/Game.FPS);
//		while (p.getHealth() != 0) {
//			try {
//				Thread.sleep(1000/24);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			yPos += speed;
//		}
	}
	
	
	
	public void update(Player p)
	{
		move(p);
	}
	
	public void draw(Graphics g)
    {
    	g.setColor(Color.WHITE);
    	g.fillRect(150 * getLane() + 50, getY() - 25, 50, 50);
    }
}