import java.awt.Color;
import java.awt.Graphics;

public class Entity {
	private Game parentGame;
	private boolean deleted;
	private int lane;
	private int yPos;
	private int maxSpeed;
	private boolean isDestroyed = false;
	private int accel;
	private int accelCd;
	private int accelRate;
	private int speed; //meters per second
	
	/*
	* @param game: game object
	* @param startLane: lane where entity will spawn
	* @param startY: y-coord where entity will spawn
	* @param startSpeed: speed at which entity starts moving at
	*/
	public Entity(Game game, int startLane, int startY, int startSpeed) {
		parentGame = game;
		lane = startLane;
		yPos = startY;
		speed = startSpeed;
		accel = 0;
		accelCd = 0;
		accelRate = 1000;
		maxSpeed = 30;
	}
	
	/*
	 * @param accel: amount by which the player's speed will increase
	 * @param accelRate: rate at which the player's speed will increase
	*/
	public Entity(Game game, int startLane, int startY, int startSpeed, int accel, int accelRate) {
		parentGame = game;
		lane = startLane;
		yPos = startY;
		speed = startSpeed;
		this.accel = accel;
		this.accelCd = 0;
		this.accelRate = accelRate;
		maxSpeed = 30;
	}
	
	/*
	 * @return returns whether or not this is touching other
	 */
	public boolean isTouching(Entity other) {
		if (other.lane == this.lane && Math.abs(other.yPos - this.yPos) < 50) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void accelerate() { // increment speed by 5 every accelRate seconds
		if (speed >= maxSpeed)
		{
			return;
		}
		if (accelCd >= accelRate)
		{
			speed += accel;
			accelCd = 0;
		}
		else
		{
			accelCd += 1000/Game.FPS;
		}
	}
	
	public void changeMaxSpeed(int newLimit) { // change max speed based on difficulty level
		maxSpeed = newLimit;
	}
	
	public void changeAccelRate(int newRate) { // change acceleration based on difficulty level
		accelRate = newRate;
	}
	public void changeAccel(int newAccel) { // change acceleration based on difficulty level
		accel = newAccel;
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
	
	
	
	public void update()
	{
		Player p = getGame().getPlayer();
		accelerate();
		move(p);
		if (yPos > Game.HEIGHT) {
			delete();
		}
		if (yPos < 0) {
			delete();
		}
	}
	
	public void delete()
	{
		deleted = true;
	}
	public boolean isDeleted() 
	{
		return deleted;
	}
	public Game getGame()
	{
		return parentGame;
	}
	
	public void draw(Graphics g)
    {
    	g.setColor(Color.WHITE);
    	g.fillRect(150 * getLane() + 50, getY() - 25, 50, 50);
    }
}