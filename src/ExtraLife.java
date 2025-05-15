public class ExtraLife extends Powerup {
	public ExtraLife(String name, String description) {
		super(name, description);
	}

	public void addLife(Player p) {
		p.addHealth(1/*max damage*/);
	}
}
