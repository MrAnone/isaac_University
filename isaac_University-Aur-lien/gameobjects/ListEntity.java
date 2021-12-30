package gameobjects;

import java.util.ArrayList;

import resources.ImagePaths;

public class ListEntity {
	private ArrayList<Entity> listEntities;
	
	public ListEntity() {
		this.listEntities= new ArrayList<Entity>();
		Entity rock = new Entity("rock",ImagePaths.ROCK);
		Entity spike = new Entity("spike",1,ImagePaths.SPIKES);
		listEntities.add(rock);
		listEntities.add(spike);
	}
	
	public void removeListEntities(int i) {
		listEntities.remove(i);
	}
	public int size() {
		return listEntities.size();
	}
	
	public Entity get(int x) {
		return listEntities.get(x);
	}

	public ArrayList<Entity> getListEntities() {
		return listEntities;
	}

	public void setListEntities(ArrayList<Entity> listEntities) {
		this.listEntities = listEntities;
	}


}
