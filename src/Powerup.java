import java.awt.Color;
import java.awt.Graphics;

public class Powerup extends Entity {
	private String name;
	private String description;
	protected boolean pickedUp = false;
	
	public Powerup(String name, String description) {
		super((int)(Math.random()*3), 0, 0);
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void update(Player p)
	{
		super.update(p);
		if (isTouching(p))
		{
			//player picks up upgrade
			p.addPowerUp(this);
			pickedUp = true;
			Game.deleteEntity(this);
		}
	}
	
	public void apply(Player p) {
		
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
