public class Entity {
	protected int lane;
	private int yPos;
	public static int maxSpeed;
	private boolean isDestroyed = false;
	public static int accelRate;
	private int speed;
	
	public Entity(int startLane, int startY) {
		lane = startLane;
		yPos = startY;
	}
	
	public boolean isTouching(Entity other) {
		if (other.lane == this.lane && Math.abs(other.yPos - this.yPos) < 10) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void changeSpeed(int speed) {
		this.speed += speed;
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
	
	public static void changeAccelRate(int newRate) { // change acceleration based on difficulty level
		accelRate = newRate;
	}
}