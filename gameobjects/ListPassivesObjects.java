package gameobjects;

import java.util.ArrayList;

import resources.ImagePaths;

public class ListPassivesObjects {
	private ArrayList<PassivesObjects> listPassivesObjects;

	public ListPassivesObjects() {
		PassivesObjects martyrOfBlood = new PassivesObjects(ImagePaths.BLOOD_OF_THE_MARTYR);
		this.listPassivesObjects = new ArrayList<PassivesObjects>();
		listPassivesObjects.add(martyrOfBlood);
	}

	public PassivesObjects get(int x) {
		return listPassivesObjects.get(x);
	}

	public ArrayList<PassivesObjects> getListPassivesObjects() {
		return listPassivesObjects;
	}

	public void setListPassivesObjects(ArrayList<PassivesObjects> listPassivesObjects) {
		this.listPassivesObjects = listPassivesObjects;
	}

}
