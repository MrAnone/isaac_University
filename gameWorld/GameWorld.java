package gameWorld;

import gameobjects.Hero;
import libraries.StdDraw;
import resources.Controls;
import resources.ImagePaths;

public class GameWorld
{
	private Donjon donjon;
	private Hero hero;

	// A world needs a hero
	public GameWorld(Hero hero)
	{
		this.hero = hero;
		donjon = new Donjon(hero);
	}

	public void processUserInput()
	{
		processKeysForMovement();
		processKeysForShoot();
	}

	public boolean gameOver()
	{
		if (hero.getCurrentHealth()==0) {
			return true;
		}
		else {
			return false;
		}
		
	}

	public void updateGameObjects()
	{
		donjon.updateRoom();
		
	}

	public void drawGameObjects()
	{
		donjon.drawRoom();
	}
	
	private void processKeysForShoot() {
		if (StdDraw.isKeyPressed(Controls.shootUp))
		{
			hero.shootUp();
		}
		if (StdDraw.isKeyPressed(Controls.shootDown))
		{
			hero.shootDown();
		}
		if (StdDraw.isKeyPressed(Controls.shootRight))
		{
			hero.shootRight();
		}
		if (StdDraw.isKeyPressed(Controls.shootLeft))
		{
			hero.shootLeft();
		}
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
		StdDraw.picture(0.05, 0.90, ImagePaths.COIN); StdDraw.textLeft(0.075, 0.90,""+hero.getCurrentCoin()+"");
		StdDraw.picture(0.04, 0.85, ImagePaths.BOMB); StdDraw.textLeft(0.065, 0.85,""+hero.getSpeed()+"");
		StdDraw.picture(0.04, 0.8, ImagePaths.KEY); StdDraw.textLeft(0.065, 0.80,""+hero.getDmg()+"");
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
