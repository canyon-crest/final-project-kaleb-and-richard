public class Bullet extends Entity {
	public Bullet(Game game, int speed) {
		super(game, game.getPlayer().getLane(), game.getPlayer().getY(), game.getPlayer().getSpeed() + speed);
	}
	

	public void update()
	{
		super.update();
		for (Entity i : getGame().getEntities()) {
			if (isTouching(i) && i instanceof Enemy) {
				((Enemy)i).die();
			}
		}
	}
}