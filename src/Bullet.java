public class Bullet extends Entity {
	public Bullet(Game game, int speed) {
		super(game, game.getPlayer().getLane(), game.getPlayer().getY(), game.getPlayer().getSpeed() + speed);
	}
	

	public void update(Game g)
	{
		super.update(g);
		for (Entity i : getGame().getEntities()) {
			if (isTouching(i) && i instanceof Enemy) {
				((Enemy)i).die();
			}
		}
	}
}