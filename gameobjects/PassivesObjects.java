package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

import java.util.Random;

import gameobjects.Hero;


public class PassivesObjects {
	private Vector2 size;
	private Vector2 position;
	private String imagePath;
	
	
	public PassivesObjects(String imagePath) {
		this.size=RoomInfos.TILE_SIZE.scalarMultiplication(0.5);;
		this.position=randomPositionObjects();
		this.imagePath=imagePath;
	}
	
	public PassivesObjects(Vector2 position,String imagePath) {
		this.size=RoomInfos.TILE_SIZE.scalarMultiplication(0.5);;
		this.position=position;
		this.imagePath=imagePath;
	}
	/**
	 * generate a random value used for the object's position
	 * @return a vector used as the object's position
	 */
	public static Vector2 randomPositionObjects() {
		Random r = new Random();
		double randomValue = 1 * r.nextDouble();
		Vector2 res = new Vector2(randomValue, randomValue);
		return res;
	}
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	/**
	 * This function up by one the damages the hero does, according to the item of the same name.
	 * @param hero is the one to have this effect
	 */
	public void BloodOfMartyr(Hero hero) {
		hero.setDmg(hero.getDmg()+1);
	}
	
	/*
	 * Getters and Setters
	 */

	public Vector2 getSize() {
		return size;
	}

	public void setSize(Vector2 size) {
		this.size = size;
	}

	public Vector2 getPosition() {
		return position;
	}

	public void setPosition(Vector2 position) {
		this.position = position;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
