public class ExtraLife extends Powerup {
	public ExtraLife(String name, String description) {
		super(name, description);
	}

	public void addLife(Player p) {
		p.addHealth(1);
	}
	
	public void apply(Player p) {
		if (super.pickedUp) {
			this.addLife(p);
		}
		super.pickedUp = false;
	}
}
