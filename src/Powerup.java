import java.awt.Color;
import java.awt.Graphics;

public class Powerup extends Entity {
	private String name;
	private String description;
	protected boolean pickedUp = false;
	
	public Powerup(Game game, String name, String description) {
		super(game, (int)(Math.random()*3), 0, 0); //powerups spawn randomly
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void update()
	{
		Player p = getGame().getPlayer();
		super.update();
		if (isTouching(p))
		{
			//player picks up upgrade
			p.addPowerUp(this);
			pickedUp = true;
			delete();
		}
	}
	
	public void apply(Player p) {
		// empty method; just here so that we can loop thru every powerup and call "apply(p)" since all subclasses have an apply method
	}
	
	public boolean isPickedUp() {
		return pickedUp;
	}
	
	public void draw(Graphics g)
	{
		g.setColor(Color.WHITE);
    	g.fillRect(150 * getLane() + 50, getY() - 25, 50, 50);
	}
}
