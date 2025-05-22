import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Gun extends Powerup {
	private BufferedImage image;
	private int ammo;
	
	public Gun(Game game, int ammoAmt, String name, String description) {
		super(game, name, description);
    	try {
			image = ImageIO.read(new File("images/gun.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.ammo = ammoAmt;
	}
	
	public void shoot() {
		if (ammo > 0) {
			ammo--;
			Bullet bullet = new Bullet(getGame(), 10); // creates new bullet object to shoot
			bullet.update();
			// car that gets shot can no longer change lanes
		}
	}
	
	public int getAmmo() {
		return ammo;
	}
	
	public void apply(Player p) {
		int totalAmmo = 0;
		for (int i = p.getPowerups().size() - 1; i >= 0; i--)
		{
			Powerup powerup = p.getPowerups().get(i);
			if (powerup instanceof Gun) {
				totalAmmo += ((Gun)powerup).getAmmo();
				p.removePowerUp(powerup);
			}
		}
		p.addPowerUp(new Gun(getGame(), totalAmmo, "gun", "spacebar to shoot"));
	}
	
	public void draw(Graphics g)
	{
    	g.drawImage(image, 150 * getLane() + 75 - 50, getY() - 50, 100, 100, null);
	}
}