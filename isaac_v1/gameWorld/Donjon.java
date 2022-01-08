package gameWorld;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

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
	private ArrayList<ArrayList<Room>> maap;
	private int x;
	private int y;
	
	public Donjon (Hero hero) {
		this.hero = hero;
		this.spawn = new Room_spawn(hero,"spawn");
		this.currentRoom = this.spawn;
		this.classic1= new Room_classic(hero,"classic1");
		this.classic2= new Room_classic(hero,"classic2");
		this.boss = new Room_boss(hero,"boss");
		this.store = new Room_store(hero,"store");
		this.x = 0;
		this.y = 1;
		//createDonjon();
		creeateDonjon();
	}
	private void creeateDonjon() {
		this.maap = new ArrayList<ArrayList<Room>>();
		ArrayList<Room> l1 = new ArrayList<Room>();
		l1.add(null);
		l1.add(spawn);
		maap.add(l1);
		
		ArrayList<Room> l2 = new ArrayList<Room>();
		l2.add(null);
		l2.add(classic1);
		maap.add(l2);
		
		ArrayList<Room> l3 = new ArrayList<Room>();
		l3.add(store);
		l3.add(classic2);
		maap.add(l3);
		
		ArrayList<Room> l4 = new ArrayList<Room>();
		l4.add(null);
		l4.add(boss);
		maap.add(l4);
		
	}
	private void createDonjon() {
		this.map = new HashMap<String,ArrayList<Room>>();
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
		//collisionDoor();
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
            if (libraries.Physics.rectangleCollision(positionAfterMoving,hero.getSize(),Door_list.get(i).getPosition(), Door_list.get(i).getSize())&&Door_list.get(i).getStatus()) {
            	if(i==0) {
            		if(x!=0) {
            			if(RoomList(x-1,y)!=null) {
            				x--;
            				currentRoom = RoomList(x,y);
            				hero.setPosition(RoomInfos.POSITION_CENTER_OF_ROOM);
            			}
            		}
            	}
            	if(i==1) {
            		if(y!=0) {
            			if(RoomList(x,y-1)!=null) {
            				y--;
            				currentRoom = RoomList(x,y);
            				hero.setPosition(RoomInfos.POSITION_CENTER_OF_ROOM);
            			}
            		}
            	}
            	if(i==2) {
            		if(x!=maap.size()-1) {
            			if(RoomList(x+1,y)!=null) {
            				x++;
            				currentRoom = RoomList(x,y);
            				hero.setPosition(RoomInfos.POSITION_CENTER_OF_ROOM);
            			}
            		}
            	}
            	if(i==3) {
            		if(y!= maap.get(x).size()-1) {
            			if(RoomList(x,y+1)!=null) {
            				y++;
            				currentRoom = RoomList(x,y);
            				hero.setPosition(RoomInfos.POSITION_CENTER_OF_ROOM);
            			}
            		}
            	}
            }
		}
	}

	private Room RoomList(int x, int y) {
		ArrayList<Room> l = maap.get(x);
		return l.get(y);
	}
	/*private void collisionDooor(){
		ArrayList<Door> Door_list = currentRoom.getDoor_list();
		for(int i =0;i<Door_list.size();i++) {
			Vector2 normalizedDirection = new Vector2(hero.getDirection());
            normalizedDirection.euclidianNormalize(hero.getSpeed());
            Vector2 positionAfterMoving =hero.getPosition().addVector(normalizedDirection);
            if (libraries.Physics.rectangleCollision(positionAfterMoving,hero.getSize(),Door_list.get(i).getPosition(), Door_list.get(i).getSize())) {
            	if (i==0) {
            		if(currentRoom!=spawn) {
            			index --;
            			currentRoom = maap.get(index);
            			hero.setPosition(RoomInfos.POSITION_CENTER_OF_ROOM);
            		}
            	}
            	if (i==2) {
            		if(currentRoom!=boss) {
            			index ++;
            			currentRoom = maap.get(index);
            			hero.setPosition(RoomInfos.POSITION_CENTER_OF_ROOM);
            		}
            	}
            	
            }
		}
	}*/
	private void collisionDooor() {
		ArrayList<Door> Door_list = currentRoom.getDoor_list();
		for(int i =0;i<Door_list.size();i++) {
			Vector2 normalizedDirection = new Vector2(hero.getDirection());
            normalizedDirection.euclidianNormalize(hero.getSpeed());
            Vector2 positionAfterMoving =hero.getPosition().addVector(normalizedDirection);
            if (libraries.Physics.rectangleCollision(positionAfterMoving,hero.getSize(),Door_list.get(i).getPosition(), Door_list.get(i).getSize())) {
            	ArrayList<Room> l = map.get(currentRoom.getID());
            	System.out.println(l.size());
            	if(l.get(i)!=null) {
            		currentRoom = l.get(i);
            		hero.setPosition(RoomInfos.POSITION_CENTER_OF_ROOM);
            	}
            }
		}
	}
	
	public Room getCurrentRoom() {
		return currentRoom;
	}
	public Room_spawn getSpawn() {
		return spawn;
	}
	public void setSpawn(Room_spawn spawn) {
		this.spawn = spawn;
	}
	public Room_classic getClassic1() {
		return classic1;
	}
	public void setClassic1(Room_classic classic1) {
		this.classic1 = classic1;
	}
	public Room_classic getClassic2() {
		return classic2;
	}
	public void setClassic2(Room_classic classic2) {
		this.classic2 = classic2;
	}
	public Room_boss getBoss() {
		return boss;
	}
	public void setBoss(Room_boss boss) {
		this.boss = boss;
	}
	public Room_store getStore() {
		return store;
	}
	public void setStore(Room_store store) {
		this.store = store;
	}
}
