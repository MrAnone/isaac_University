package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class PassivesObjects extends Objects{

	
	public PassivesObjects(int id,String imagePath) {
		super(id,imagePath);

	}
	
	public PassivesObjects(int id, Vector2 position, String imagePath) {
		super(id,position,imagePath);
		}
	/**
	 * generate a random value used for the object's position
	 * @return a vector used as the object's position
	 */
	public static Vector2 randomPositionObjects() {
		Random r = new Random();
		double randomValue1 =   ThreadLocalRandom.current().nextDouble(0.1, 0.9);
		double randomValue2 =   ThreadLocalRandom.current().nextDouble(0.1, 0.9);
		Vector2 res = new Vector2(randomValue1, randomValue2);
		return res;
	}
	
	public void drawGameObject()
	{
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	
	
	/*
	 * Getters and Setters
	 */

	@Override
	public int getId() {
		return super.getId();
	}

	@Override
	public void setId(int id) {
		super.setId(id);
	}

	@Override
	public Vector2 getSize() {
		return super.getSize();
	}

	@Override
	public void setSize(Vector2 size) {
		super.setSize(size);
	}

	@Override
	public Vector2 getPosition() {
		return super.getPosition();
	}

	@Override
	public void setPosition(Vector2 position) {
		super.setPosition(position);
	}

	@Override
	public String getImagePath() {
		return super.getImagePath();
	}

	@Override
	public void setImagePath(String imagePath) {
		super.setImagePath(imagePath);
	}

}
