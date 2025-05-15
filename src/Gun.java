public class Gun extends Powerup {
	private int ammo;
	public static int totalAmmo = 0;
	
	public Gun(int ammoAmt, String name, String description) {
		super(name,description);
		this.ammo = ammoAmt;
	}
	
	public void shoot() {
		if (totalAmmo > 0) {
			totalAmmo--;
			// car that gets shot can no longer change lanes
		}
	}
	
	public int getAmmo() {
		return ammo;
	}
	
	public void getAmmo(Player p) { // if player already has a gun and picks up another gun powerup then just get additional ammo
		totalAmmo += ammo;
	}
}