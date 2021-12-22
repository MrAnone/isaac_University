package gameWorld;

import gameobjects.Hero;
import libraries.StdDraw;
import resources.Controls;
import resources.ImagePaths;


public class GameWorld
{
	private Room currentRoom;
	private Hero hero;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		currentRoom = new Room(hero);
	}

	public void processUserInput()
	{
		processKeysForMovement();
	}

	public boolean gameOver()
	{
		return false;
	}

	public void updateGameObjects()
	{
		currentRoom.updateRoom();
	}

	public void drawGameObjects()
	{
		currentRoom.drawRoom();
	}

	/**
	 * Allows player to see the hero's stats such as health, coin, speed etc.
	 */
	public void drawHUD() {
		double lifeHud= hero.getCurrentHealth();
		double a=0;
		if(lifeHud%2==0) {
			for(int i=0;i<lifeHud/2;i++) {
				StdDraw.picture(0.05+a, 0.95, ImagePaths.HEART_HUD);
				a+=0.05;
			}			
		}
		else {
			for(int i =0;i<(lifeHud-1)/2;i++) {
				StdDraw.picture(0.05+a, 0.95, ImagePaths.HEART_HUD);
				a+=0.05;
			}
			StdDraw.picture(0.05+a, 0.95, ImagePaths.HALF_HEART_HUD);
		}
		StdDraw.setPenColor();
		StdDraw.picture(0.05, 0.90, ImagePaths.COIN_HUD); StdDraw.textLeft(0.075, 0.90,""+hero.getCurrentCoin()+"");
		StdDraw.picture(0.04, 0.85, ImagePaths.SPEED_HUD); StdDraw.textLeft(0.065, 0.85,""+hero.getSpeed()+"");
		StdDraw.picture(0.04, 0.8, ImagePaths.DMG_HUD); StdDraw.textLeft(0.065, 0.80,""+hero.getDmg()+"");
	}

	/*
	 * Keys processing
	 */

	private void processKeysForMovement()
	{
		if (StdDraw.isKeyPressed(Controls.goUp))
		{
			hero.goUpNext();
		}
		if (StdDraw.isKeyPressed(Controls.goDown))
		{
			hero.goDownNext();
		}
		if (StdDraw.isKeyPressed(Controls.goRight))
		{
			hero.goRightNext();
		}
		if (StdDraw.isKeyPressed(Controls.goLeft))
		{
			hero.goLeftNext();
		}
	}
}