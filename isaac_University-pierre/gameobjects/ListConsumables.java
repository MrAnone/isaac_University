package gameobjects;

import java.util.ArrayList;

import resources.ImagePaths;

public class ListConsumables {
	private ArrayList<Consumable_Objects> listConsumableObjects;
	
	public ListConsumables() {
		Consumable_Objects fullHeart= new Consumable_Objects(2,ImagePaths.HEART_PICKABLE);
		Consumable_Objects halfHeart= new Consumable_Objects(1,ImagePaths.HALF_HEART_PICKABLE);
		Consumable_Objects penny= new Consumable_Objects(1,ImagePaths.COIN);
		Consumable_Objects nickel= new Consumable_Objects(5,ImagePaths.NICKEL);
		Consumable_Objects dime= new Consumable_Objects(10,ImagePaths.DIME);
		this.listConsumableObjects= new ArrayList<Consumable_Objects>();
		listConsumableObjects.add(fullHeart);
		listConsumableObjects.add(halfHeart);
		listConsumableObjects.add(penny);
		listConsumableObjects.add(nickel);
		listConsumableObjects.add(dime);
	}

	public ArrayList<Consumable_Objects> getListConsumableObjects() {
		return listConsumableObjects;
	}
	public int size() {
		return listConsumableObjects.size();
	}

	public void setListConsumableObjects(ArrayList<Consumable_Objects> listConsumableObjects) {
		this.listConsumableObjects = listConsumableObjects;
	}
	
	public Consumable_Objects get(int x) {
		return listConsumableObjects.get(x);
	}


}
