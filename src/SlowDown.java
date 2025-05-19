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
	
	public void apply(Player p) {
		if (super.pickedUp) {
			this.reduceSpeed(p, (int)(Math.random()*15)+1);
		}
		super.pickedUp = false;
	}
}
