import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ExtraLife extends Powerup {
	private BufferedImage image;
	public ExtraLife(Game game, String name, String description) {
		super(game, name, description);
    	try {
			image = ImageIO.read(new File("images/extra_life.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addLife(Player p) {
		p.addHealth(1);
	}
	
	public void apply(Player p) { // applies powerup effect to player
		if (super.pickedUp) {
			this.addLife(p);
		}
		super.pickedUp = false;
	}
	
	public void draw(Graphics g)
	{
    	g.drawImage(image, 150 * getLane() + 75 - 50, getY() - 50, 100, 100, null);
	}
}
