public class Bullet extends Entity {
	public Bullet(Game game, int speed) {
		super(game, game.getPlayer().getLane(), game.getPlayer().getY(), game.getPlayer().getSpeed() + speed); // initiates the bullet where the player is
	}
	
	@Override
	public void update() // calls update function while also checking if bullet has hit an enemy car
	{
		super.update();
		for (Entity i : getGame().getEntities()) {
			if (isTouching(i) && i instanceof Enemy) {
				((Enemy)i).die();
			}
		}
	}
}