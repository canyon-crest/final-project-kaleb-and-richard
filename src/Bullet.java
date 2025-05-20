public class Bullet extends Entity {
	public Bullet(Player p) {
		super(p.getLane(),p.getY(),50);
	}
	

	public void update(Player p)
	{
		Game game = new Game();
		super.update(p);
		for (Entity i : game.getEntities()) {
			if (isTouching(i) && i instanceof Enemy) {
				((Enemy)i).die();
			}
		}
	}
}