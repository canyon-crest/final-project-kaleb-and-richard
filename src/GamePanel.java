import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel { // displays the game
	private Font font;
	private Color lineColor;
	private Color roadColor;
	private Game game;
	private int ypos;
	public GamePanel(Game game)
	{
		font = new Font("Arial", Font.PLAIN, 16);
		lineColor = new Color(200, 200, 200);
		roadColor = new Color(100, 100, 100);
		this.game = game;
	}
	
	public void update(Graphics g) //gets updated by Game.update each frame
	{
		repaint(g);
	}
	public void repaint(Graphics g)
	{
		paintComponent(g);
	}
	public void paintComponent(Graphics g) //paints the scene
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
	
	public void drawBackground(Graphics g) //draws the road and road lines
	{
		ypos += game.getPlayer().getSpeed() * (50.0/Game.FPS);
		ypos = ypos % 200;
		g.setColor(roadColor);
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		g.setColor(lineColor);
		g.fillRect(149, ypos - 200, 2, 125);
		g.fillRect(299, ypos - 200, 2, 125);
		g.fillRect(149, ypos, 2, 125);
		g.fillRect(299, ypos, 2, 125);
		g.fillRect(149, ypos + 200, 2, 125);
		g.fillRect(299, ypos + 200, 2, 125);
		g.fillRect(149, ypos + 400, 2, 125);
		g.fillRect(299, ypos + 400, 2, 125);
	}
	
	public void drawUI(Graphics g) //displays ui information
	{
		g.setFont(font);
		g.setColor(Color.WHITE);
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
