package gameWorld;

import java.util.ArrayList;

import gameobjects.Fly;
import gameobjects.Hero;
import resources.HeroInfos;
import gameobjects.Spider;
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
		processKeysForCheated();
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
		StdDraw.picture(0.05, 0.90, ImagePaths.COIN_HUD); StdDraw.textLeft(0.075, 0.90,""+hero.getCurrentCoin());
		StdDraw.picture(0.04, 0.85, ImagePaths.SPEED_HUD); StdDraw.textLeft(0.065, 0.85,""+hero.getSpeed());
		StdDraw.picture(0.04, 0.8, ImagePaths.DMG_HUD); StdDraw.textLeft(0.065, 0.80,""+hero.getDamage());
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
	private void processKeysForCheated() {
		if (StdDraw.isKeyPressed(Controls.invincible))
		{
			hero.setInvincible(true);
			hero.setCanBeHurt(false);
		}
		else {
			hero.setInvincible(false);
			hero.setCanBeHurt(true);
		}
		if (StdDraw.isKeyPressed(Controls.fast))
		{
			hero.setSpeed(HeroInfos.ISAAC_FAST);
		}
		else {
			hero.setSpeed(HeroInfos.ISAAC_SPEED);
		}
		if (StdDraw.isKeyPressed(Controls.kill_all))
		{
			donjon.getCurrentRoom().setFly_list(new ArrayList <Fly>());
			donjon.getCurrentRoom().setSpider_list(new ArrayList <Spider>());
			if(donjon.getCurrentRoom().getID().equalsIgnoreCase("boss")) {
				for(int i=0;i<donjon.getBoss().getBossList().size();i++) {
					donjon.getBoss().getBossList().get(i).setLife(0);					
				}
			}
		}
		if (StdDraw.isKeyPressed(Controls.dommage_max))
		{
			hero.setDamage(5);
		}
		else {
			hero.setDamage(hero.getDamage());
		}
		if (StdDraw.isKeyPressed(Controls.money))
		{
			hero.setCurrentCoin(hero.getCurrentCoin()+10);
		}
	}
}
