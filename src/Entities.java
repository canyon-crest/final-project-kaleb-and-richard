public class Entities {
	protected int lane;
	private int yPos;
	public static int maxSpeed;
	private boolean isDestroyed = false;
	public static int accelRate;
	private int speed;
	
	public Entities(int startLane, int startY) {
		lane = startLane;
		yPos = startY;
	}
	
	public boolean isTouching(Entities other) {
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
	
	private void accelerate() { // increment speed by 5 every accelRate seconds
		
	}
	
	public static void changeMaxSpeed(int newLimit) { // change max speed based on difficulty level
		maxSpeed = newLimit;
	}
	
	public static void changeAccelRate(int newRate) { // change acceleration based on difficulty level
		accelRate = newRate;
	}
}