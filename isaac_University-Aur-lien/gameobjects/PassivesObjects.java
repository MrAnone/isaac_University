package gameobjects;

import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

import java.util.Random;


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
		double randomValue1 =  r.nextDouble();
		double randomValue2 =  r.nextDouble();
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
		// TODO Auto-generated method stub
		return super.getId();
	}

	@Override
	public void setId(int id) {
		// TODO Auto-generated method stub
		super.setId(id);
	}

	@Override
	public Vector2 getSize() {
		// TODO Auto-generated method stub
		return super.getSize();
	}

	@Override
	public void setSize(Vector2 size) {
		// TODO Auto-generated method stub
		super.setSize(size);
	}

	@Override
	public Vector2 getPosition() {
		// TODO Auto-generated method stub
		return super.getPosition();
	}

	@Override
	public void setPosition(Vector2 position) {
		// TODO Auto-generated method stub
		super.setPosition(position);
	}

	@Override
	public String getImagePath() {
		// TODO Auto-generated method stub
		return super.getImagePath();
	}

	@Override
	public void setImagePath(String imagePath) {
		// TODO Auto-generated method stub
		super.setImagePath(imagePath);
	}

}
