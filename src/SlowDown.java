public class SlowDown extends Powerup {	
	public void reduceSpeed(Player p, int s) {
		if (p.getSpeed() > s) {
			p.setSpeed(p.getSpeed() - s);
		}
	}
}
