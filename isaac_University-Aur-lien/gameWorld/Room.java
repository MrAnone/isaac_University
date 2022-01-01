package gameWorld;


import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import gameobjects.Hero;
import gameobjects.ListEntity;
import gameobjects.Objects;
import gameobjects.Door;
import gameobjects.Entity;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;

public abstract class Room {
	private Hero hero;
	private ArrayList<Objects> listObjectOnFloor;
	private ArrayList<Entity> listEntitiesOnRoom;
	private boolean roomAlredayCleared;
	private boolean hurtBySpikes;
	private ArrayList<Door> Door_list;
	private String ID;

	public Room(Hero hero,String ID) {
		this.hero = hero;
		this.ID=ID;
		Door_list = new ArrayList <Door>();
		this.listObjectOnFloor = new ArrayList<Objects>();
		this.roomAlredayCleared = false;
		this.listEntitiesOnRoom = new ArrayList<Entity>();	
		CreateDoor();
		hurtBySpikes=false;
	}

	public void Larmes() {
		for(int i=0;i<hero.tears_size();i++) {
			hero.tears_get(i).drawGameObject();
		}
	}

	/**
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom() {

	}

	/**
	 * Add an item ,from all the items in the game, on the floor if the monsters are
	 * dead
	 */
	public abstract void roomCleaned();

	public void createListEntitiesOnRoom() {
		ListEntity listFullEntities = new ListEntity();
		int randomMax = ThreadLocalRandom.current().nextInt(0, 4);
		for (int i = 0; i < randomMax; i++) {
			int randomIndex = ThreadLocalRandom.current().nextInt(0, listFullEntities.size());
			listEntitiesOnRoom.add(listFullEntities.get(randomIndex));
		}

	}
	public void canGetHurtBySpikes() {
		if(hurtBySpikes) {
			hurtBySpikes=false;
		}else {
			hurtBySpikes=true;
		}
		
	}

	public abstract void collisionObjectAndHero();
	

	public void collisionEntitiesAndHero() {
		for (int i = 0; i < listEntitiesOnRoom.size(); i++) {
			Vector2 normalizedDirection = new Vector2(hero.getDirection());
			normalizedDirection.euclidianNormalize(hero.getSpeed());
			Vector2 positionAfterMoving = hero.getPosition().addVector(normalizedDirection);
			if (libraries.Physics.rectangleCollision(positionAfterMoving, hero.getSize(),
					listEntitiesOnRoom.get(i).getPosition(), listEntitiesOnRoom.get(i).getSize())) {
				String id = listEntitiesOnRoom.get(i).getId();
				switch (id) {
				case "rock":
					hero.setDirection(new Vector2());
					break;
				default:
					hero.setCurrentHealth(hero.getCurrentHealth() - 1);// too fast
					
					break;
				}
			}
		}
	}

	public void collisionLarmes_Mur() {
		for(int i=0;i<hero.tears_size();i++) {
			Vector2 normalizedDirection = new Vector2(hero.tears_get(i).getDirection());
			normalizedDirection.euclidianNormalize(hero.tears_get(i).getSpeed());
			Vector2 positionAfterMoving = hero.tears_get(i).getPosition().addVector(normalizedDirection);
			if (!libraries.Physics.ZonedeJeu(positionAfterMoving,hero.tears_get(i).getSize())){
				hero.tears_remove(i);
			}
		}
	}

	public void makeLarmesPlay() {
		if(hero.getTimeLarmes()==0) {
			hero.setShoot(false);
			hero.setTimeLarmes(resources.HeroInfos.LARMES_FRAME);
		}
		else if (hero.getShoot()) {
			hero.setTimeLarmes(hero.getTimeLarmes()-1);
		}
		for(int i=0;i<hero.tears_size();i++) {
			hero.tears_get(i).setScope(hero.tears_get(i).getScope()-1);
			if (hero.tears_get(i).getScope()==0) {
				hero.tears_remove(i);
			}
			else {
				hero.tears_get(i).updateGameObject();
			}
		}
	}

	public void makeHeroPlay() {
		hero.updateGameObject();
	}

	/*
	 * Drawing
	 */
	public void drawRoom() {

	}
	
	public void CreateDoor() {
		Door d1 = new Door (new Vector2(0.071,0.5),new Vector2(0.2,0.15), true, ImagePaths.OPENED_DOOR,90.0,"East");
		Door d2 = new Door (new Vector2(0.5,0.929),new Vector2(0.2,0.15), true, ImagePaths.CLOSED_DOOR,0.0,"North");
		Door d3 = new Door (new Vector2(0.929,0.5),new Vector2(0.2,0.15), true, ImagePaths.OPENED_DOOR,-90.0,"West");
		Door d4 = new Door (new Vector2(0.5,0.071),new Vector2(0.2,0.15), true, ImagePaths.CLOSED_DOOR,180.0,"South");
		Door_list.add(d1);
		Door_list.add(d2);
		Door_list.add(d3);
		Door_list.add(d4);
	}
	public void DrawDoor() {
		for(int i =0;i<Door_list.size();i++) {
			Door_list.get(i).drawGameObject();
		}
	}
	/*
	 * getters and Setters
	 */

	public ArrayList<Objects> getListObjectOnFloor() {
		return listObjectOnFloor;
	}

	public void setListObjectOnFloor(ArrayList<Objects> listObject) {
		this.listObjectOnFloor = listObject;
	}

	public boolean isRoomAlredayCleared() {
		return roomAlredayCleared;
	}

	public void setRoomAlredayCleared(boolean roomAlredayCleared) {
		this.roomAlredayCleared = roomAlredayCleared;
	}

	public ArrayList<Entity> getListEntitiesOnRoom() {
		return listEntitiesOnRoom;
	}

	public void setListEntitiesOnRoom(ArrayList<Entity> listEntitiesOnRoom) {
		this.listEntitiesOnRoom = listEntitiesOnRoom;
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public ArrayList<Door> getDoor_list() {
		return Door_list;
	}

	public void setDoor_list(ArrayList<Door> door_list) {
		Door_list = door_list;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

}
