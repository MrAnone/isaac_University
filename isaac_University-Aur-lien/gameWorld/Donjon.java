package gameWorld;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import gameobjects.Door;
import gameobjects.Hero;
import libraries.Vector2;
import resources.RoomInfos;

public class Donjon {

	private Room currentRoom;
	private Hero hero;
	private Room_spawn spawn;
	private Room_classic classic;
	private Room_boss boss;
	private Room_store store;
	private ArrayList<Room> map;
	private int nbrRoom;

	public Donjon(Hero hero) {
		// currentRoom = new Room_classic(hero);
		this.hero = hero;
		this.spawn = new Room_spawn(hero, "spawn");
		this.classic= new Room_classic(hero,"classic");
		this.boss = new Room_boss(hero, "boss");
		this.store = new Room_store(hero, "store");
		currentRoom = this.classic;
		this.map = new ArrayList<Room>();
		this.nbrRoom = 4;
		createDonjon();

	}

	public void updateRoom() {	
		collisionDoor();
		currentRoom.updateRoom();
		
		
	}

	public void drawRoom() {
		currentRoom.drawRoom();
	}

	private void createDonjon() {
		for (int i = 0; i < nbrRoom; i++) {
			if(i==0) {
				map.add(this.spawn);
			}
			else if(i==1) {
				map.add(this.store);
			}
			else if (i== nbrRoom-1) {
				map.add(this.boss);
			}
			else {
				map.add(this.classic);
			}
		}
	}

	private void collisionDoor() {
		ArrayList<Door> Door_list = currentRoom.getDoor_list();
		for (int i = 0; i < Door_list.size(); i++) {
			Vector2 normalizedDirection = new Vector2(hero.getDirection());
			normalizedDirection.euclidianNormalize(hero.getSpeed());
			Vector2 positionAfterMoving = hero.getPosition().addVector(normalizedDirection);
			if (libraries.Physics.rectangleCollision(positionAfterMoving, hero.getSize(),
					Door_list.get(i).getPosition(), Door_list.get(i).getSize())) {
				Door currentDoor = Door_list.get(i);
				switch (currentDoor.getID()) {
					case "East":
						if(i-1 >=0 && currentDoor.getStatus() && map.get(i+1) != null) {
							currentRoom = map.get(i+1);
						}
						break;
					case "West":
						if(i+1 < nbrRoom && currentDoor.getStatus() && map.get(i-1) != null) {
							currentRoom = map.get(i-1);
						}
						break;
					case "North":
						//if(currentDoor.getStatus() && map.get(i+1) != null) {
						//	currentRoom = map.get(i+1);
						//}
						break;
					case "South":
						//if(currentDoor.getStatus() && map.get(i-1) != null) {
						//	currentRoom = map.get(i-1);
						//}
						break;

				}
			}
		}
	}

	public int getNbrRoom() {
		return nbrRoom;
	}

	public void setNbrRoom(int nbrRoom) {
		this.nbrRoom = nbrRoom;
	}

}
