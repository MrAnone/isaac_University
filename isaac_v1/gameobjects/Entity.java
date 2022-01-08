package gameobjects;


import java.util.concurrent.ThreadLocalRandom;

import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

public class Entity {
	private String id;
	private Vector2 size;
	private Vector2 position;
	private boolean breakable;
	private double dmg;
	private String imagePath;
	
	/**
	 * To create rocks
	 * @param id make it easier to differentiate objects
	 */
	public Entity(String id,String imagePath) {
		this.id=id;
		this.size=RoomInfos.TILE_SIZE;
		this.position=randomPositionEntity();
		this.breakable=true;
		this.dmg=0;
		this.imagePath= imagePath;
	}
	/**
	 * To create spikes on the floor
	 * @param id make it easier to differentiate objects
	 * @param dmg is a double to hurt the hero when step on it.
	 */
	public Entity(String id,double dmg,String imagePath) {
		this.id=id;
		this.size=RoomInfos.TILE_SIZE;
		this.position=randomPositionEntity();
		this.breakable=false;
		this.dmg=dmg;
		this.imagePath= imagePath;
	}
	
	/**
	 * Create a random position
	 * @return a position made of 2 random double
	 */
	public Vector2 randomPositionEntity() {
		double randomValue1 =   ThreadLocalRandom.current().nextDouble(0.1, 0.9);
		double randomValue2 =   ThreadLocalRandom.current().nextDouble(0.1, 0.9);
		Vector2 res = new Vector2(randomValue1, randomValue2);
		return res;
	}
	
	public  void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	/*
	 * Getters and Setters
	 */
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public boolean isBreakable() {
		return breakable;
	}
	public void setBreakable(boolean breakable) {
		this.breakable = breakable;
	}
	public double getDmg() {
		return dmg;
	}
	public void setDmg(double dmg) {
		this.dmg = dmg;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
