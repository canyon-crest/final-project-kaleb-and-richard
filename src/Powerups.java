public class Powerups extends Entities {
	private String name;
	private String description;
	
	public Powerups(String name, String description) {
		super((int)(Math.random()*3),0);
		this.name = name;
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
}
