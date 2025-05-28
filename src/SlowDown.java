import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SlowDown extends Powerup {	
	private BufferedImage image;
	public SlowDown(Game game, String name, String description) {
		super(game, name, description);
    	try {
			image = ImageIO.read(new File("images/slow-down.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void reduceSpeed(Player p, int s) {
		if (p.getSpeed() - s > 5) {
			p.setSpeed(p.getSpeed() - s);
		}
		else
			p.setSpeed(5);
		for (Entity i : getGame().getEntities())
		{
			i.setSpeed((int)(p.getSpeed() * 0.75));
		}
	}
	
	public void apply(Player p) {
		if (super.pickedUp) {
			this.reduceSpeed(p, (int)(Math.random()*10)+1);
		}
		super.pickedUp = false;
	}
	
	public void draw(Graphics g)
	{
    	g.drawImage(image, 150 * getLane() + 75 - 50, getY() - 50, 100, 100, null);
	}
}
