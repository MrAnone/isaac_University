package gameobjects;

import java.util.ArrayList;

import resources.ImagePaths;
import resources.RoomInfos;

public class ListObjects {
	private ArrayList<Objects> listObjects;
	
	public ListObjects() {
		this.listObjects = new ArrayList<Objects>();
		PassivesObjects bloodOfMartyr = new PassivesObjects(0,ImagePaths.BLOOD_OF_THE_MARTYR);
		bloodOfMartyr.setSize(RoomInfos.TILE_SIZE.scalarMultiplication(0.5)); // too small otherwise
		PassivesObjects hpUp = new PassivesObjects(1,ImagePaths.HP_UP);
		hpUp.setSize(RoomInfos.TILE_SIZE.scalarMultiplication(0.5)); // too small otherwise
		Consumable_Objects fullHeart= new Consumable_Objects(2,ImagePaths.HEART_PICKABLE,2);
		Consumable_Objects halfHeart= new Consumable_Objects(3,ImagePaths.HALF_HEART_PICKABLE,1);
		Consumable_Objects penny= new Consumable_Objects(4,ImagePaths.COIN,1);
		Consumable_Objects nickel= new Consumable_Objects(5,ImagePaths.NICKEL,5);
		Consumable_Objects dime= new Consumable_Objects(6,ImagePaths.DIME,10);
		listObjects.add(bloodOfMartyr);
		listObjects.add(hpUp);
		listObjects.add(fullHeart);
		listObjects.add(halfHeart);
		listObjects.add(penny);
		listObjects.add(nickel);
		listObjects.add(dime);
	}
	public void removeListObjects(int i) {
		listObjects.remove(i);
	}
	public int size() {
		return listObjects.size();
	}
	
	public Objects get(int x) {
		return listObjects.get(x);
	}

	public ArrayList<Objects> getListPassivesObjects() {
		return listObjects;
	}

	public void setListPassivesObjects(ArrayList<Objects> listObjects) {
		this.listObjects = listObjects;
	}

}
