import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel { // displays the game
	private Font font;
	private Game game;
	private int ypos;
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
		if (game.getEntities() == null || game.getPlayer() == null)
			return;
		drawBackground(g);
		
		for (Entity e : game.getEntities())
		{
			e.draw(g);
		}
		game.getPlayer().draw(g);
		
		drawUI(g);
	}
	
	public void drawBackground(Graphics g)
	{
		ypos += game.getPlayer().getSpeed() * (50.0/Game.FPS);
		ypos = ypos % 200;
		g.setColor(new Color(100, 100, 100));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(new Color(200, 200, 200));
		g.fillRect(149, ypos - 175, 2, 150);
		g.fillRect(299, ypos - 175, 2, 150);
		g.fillRect(149, ypos + 25, 2, 150);
		g.fillRect(299, ypos + 25, 2, 150);
		g.fillRect(149, ypos + 225, 2, 150);
		g.fillRect(299, ypos + 225, 2, 150);
		g.fillRect(149, ypos + 425, 2, 150);
		g.fillRect(299, ypos + 425, 2, 150);
	}
	
	public void drawUI(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.setFont(font);
		g.drawString("HI-SCORE: " + Game.highScoreHolder + " " + Game.highScore, 10, 25);
		g.drawString("SCORE: " + game.getScore(), 10, 50);
		g.drawString("SPEED: " + game.getPlayer().getSpeed(), 10, 75);
		g.drawString("HEALTH: " + game.getPlayer().getHealth(), 10, 100);
		g.drawString("AMMO: " + game.getPlayer().getAmmo(), 10, 125);
		if (game.gameOver)
		{
			g.drawString("GAME OVER", 175, 280);
			g.drawString("spacebar to restart", 160, 300);
		}
	}
}
