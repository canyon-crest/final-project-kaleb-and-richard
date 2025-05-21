public class ExtraLife extends Powerup {
	public ExtraLife(Game game, String name, String description) {
		super(game, name, description);
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
