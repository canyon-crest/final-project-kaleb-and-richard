import java.util.Timer;

public class Enemy extends Entity {
	public Enemy() {
		super((int)(Math.random()*3),0);
	}
	
	public void removeHealth(Player player) {
		player.removeHealth(1);
	}
	
	public void changeLane() {
		super.lane = (int)(Math.random()*3);
	}
	
	public void move(Player p) { // increment yPos by speed every x amount of time
		while (p.getHealth() != 0) {
			try {
				Thread.sleep(1000/24);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			p.moveY(speed);
		}
	}
}
