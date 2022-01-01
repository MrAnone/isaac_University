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
	private Room_classic classic1;
	private Room_classic classic2;
	private Room_boss boss;
	private Room_store store;
	private Map<String,ArrayList<Room>> map;
	
	public Donjon (Hero hero) {
		//currentRoom = new Room_classic(hero);
		this.hero = hero;
		this.spawn = new Room_spawn(hero,"spawn");
		currentRoom = this.spawn;
		this.classic1= new Room_classic(hero,"classic1");
		this.classic2= new Room_classic(hero,"classic2");
		this.boss = new Room_boss(hero,"boss");
		this.store = new Room_store(hero,"store");
		createDonjon();
	}
	private void createDonjon() {
		this.map = new TreeMap<String,ArrayList<Room>>();
		ArrayList<Room> l1 = new ArrayList<Room>();
		l1.add(null);
		l1.add(null);
		l1.add(classic1);
		l1.add(null);
		ArrayList<Room> l2 = new ArrayList<Room>();
		l1.add(spawn);
		l1.add(null);
		l1.add(classic2);
		l1.add(null);
		ArrayList<Room> l3 = new ArrayList<Room>();
		l1.add(classic2);
		l1.add(store);
		l1.add(boss);
		l1.add(null);
		ArrayList<Room> l4 = new ArrayList<Room>();
		l1.add(classic2);
		l1.add(null);
		l1.add(null);
		l1.add(null);
		ArrayList<Room> l5 = new ArrayList<Room>();
		l1.add(null);
		l1.add(null);
		l1.add(null);
		l1.add(classic2);
		map.put("spawn",l1);
		map.put("classic1", l2);
		map.put("classic2", l3);
		map.put("boss", l4);
		map.put("store", l5);
	}
	public void updateRoom()
	{
		currentRoom.updateRoom();
		collisionDoor();
	}
	public void drawRoom()
	{
		currentRoom.drawRoom();
	}
	private void collisionDoor() {
		ArrayList<Door> Door_list = currentRoom.getDoor_list();
		for(int i =0;i<Door_list.size();i++) {
			Vector2 normalizedDirection = new Vector2(hero.getDirection());
            normalizedDirection.euclidianNormalize(hero.getSpeed());
            Vector2 positionAfterMoving =hero.getPosition().addVector(normalizedDirection);
            if (libraries.Physics.rectangleCollision(positionAfterMoving,hero.getSize(),Door_list.get(i).getPosition(), Door_list.get(i).getSize())) {
            	ArrayList<Room> l = map.get(currentRoom.getID());
            	if(l.get(i)!=null) {
            		currentRoom = l.get(i);
            		hero.setPosition(RoomInfos.POSITION_CENTER_OF_ROOM);
            		break;
            	}
            }
		}
	}
	
	
	
	
	
	
	
	
	
	
}
