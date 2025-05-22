public class Gun extends Powerup {
	private int ammo;
	public static int totalAmmo = 0;
	
	public Gun(Game game, int ammoAmt, String name, String description) {
		super(game, name, description);
		this.ammo = ammoAmt;
	}
	
	public void shoot() {
		if (totalAmmo > 0) {
			totalAmmo--;
			Bullet bullet = new Bullet(getGame(), 10);
			bullet.update();
			// car that gets shot can no longer change lanes
		}
	}
	
	public int getAmmo() {
		return ammo;
	}
	
	public void getAmmo(Player p) { // if player already has a gun and picks up another gun powerup then just get additional ammo
		totalAmmo += ammo;
	}
	
	public void apply(Player p) {
		if (super.pickedUp) {
			// add gun (construct it with Random ammoAmt) to powerUps ArrayList and increase totalAmmo
			boolean alreadyHasGun = false;
			for (Powerup powerup : p.getPowerups()) {
				if (powerup instanceof Gun) {
					((Gun) powerup).ammo += this.ammo;
					totalAmmo += this.ammo;
					alreadyHasGun = true;
					break;
				}
			}
			if (!alreadyHasGun) {
				p.addPowerUp(this);
				totalAmmo += this.ammo;
			}
		}
	}
}