package gameobjects;

import java.util.Random;

import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

public class Consumable_Objects extends Objects {
	private double value;

	public Consumable_Objects(int id, String imagePath,double value) {
		super(id,imagePath);
		this.value = value;
	}

	// A second constructor for the shopRoom where position will be fixed by the
	// room.
	public Consumable_Objects(int id, String imagePath, Vector2 position,double value) {
		super(id,position, imagePath);
		this.value = value;
	}

	public static Vector2 randomPositionObjects() {
		Random r = new Random();
		double randomValue1 = r.nextDouble();
		double randomValue2 = r.nextDouble();
		Vector2 res = new Vector2(randomValue1, randomValue2);
		return res;
	}

	public void drawGameObject() {
		StdDraw.picture(getPosition().getX(), getPosition().getY(), getImagePath(), getSize().getX(), getSize().getY(),
				0);
	}
	
	

	/*
	 * Getters and Setters
	 */

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

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
