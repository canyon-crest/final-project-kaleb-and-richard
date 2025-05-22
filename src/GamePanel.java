import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel { // displays the game
	private Font font;
	private Game game;
	public GamePanel(Game game)
	{
		font = new Font("Arial", Font.PLAIN, 16);
		this.game = game;
	}
	
	public void update(Graphics g)
	{
		repaint(g);
	}
	public void repaint(Graphics g)
	{
		paintComponent(g);
	}
	public void paintComponent(Graphics g)
	{
		g.setColor(new Color(100, 100, 100));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(new Color(200, 200, 200));
		g.fillRect(149, 0, 2, Game.HEIGHT);
		g.fillRect(299, 0, 2, Game.HEIGHT);
		
		if (game.getEntities() == null || game.getEntities() == null)
			return;
		for (Entity e : game.getEntities())
		{
			e.draw(g);
		}
		game.getPlayer().draw(g);
		
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("SCORE: " + game.getScore(), 10, 25);
		g.drawString("SPEED: " + game.getPlayer().getSpeed(), 10, 50);
	}
}
