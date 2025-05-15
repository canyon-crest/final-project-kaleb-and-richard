public class SlowDown extends Powerup {	
	public SlowDown(String name, String description) {
		super(name, description);
		// TODO Auto-generated constructor stub
	}

	public void reduceSpeed(Player p, int s) {
		if (p.getSpeed() > s) {
			p.setSpeed(p.getSpeed() - s);
		}
	}
}
