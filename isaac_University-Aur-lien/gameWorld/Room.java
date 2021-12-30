package gameWorld;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;
import gameobjects.Hero;
import gameobjects.Larmes;
import gameobjects.ListEntity;
import gameobjects.Objects;
import gameobjects.Entity;
import libraries.Vector2;

public abstract class Room {
	private Hero hero;
	public static ArrayList<Larmes> larmes_list;
	private ArrayList<Objects> listObjectOnFloor;
	private ArrayList<Entity> listEntitiesOnRoom;
	private boolean roomAlredayCleared;

	public Room(Hero hero) {
		this.hero = hero;
		larmes_list = new ArrayList<Larmes>();
		this.listObjectOnFloor = new ArrayList<Objects>();
		this.roomAlredayCleared = false;
		this.listEntitiesOnRoom = new ArrayList<Entity>();
	}

	public void Larmes() {
		for (int i = 0; i < larmes_list.size(); i++) {
			larmes_list.get(i).drawGameObject();
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

	/*
	 * public void createListObject() { ListObjects listFullObjects = new
	 * ListObjects(); int randomMax = ThreadLocalRandom.current().nextInt(0, 2 + 1);
	 * for (int i = 0; i < randomMax; i++) { int randomIndex =
	 * ThreadLocalRandom.current().nextInt(0, listFullObjects.size());
	 * listObjectOnFloor.add(listFullObjects.get(randomIndex));
	 * 
	 * } }
	 * 
	 * public Objects randomObject() { int randomNum =
	 * ThreadLocalRandom.current().nextInt(0, listObject.size() + 1); return
	 * listObject.get(randomNum); }
	 */

	public void collisionObjectAndHero() {
		for (int i = 0; i < listObjectOnFloor.size(); i++) {
			if (libraries.Physics.rectangleCollision(hero.getPosition(), hero.getSize(),
					listObjectOnFloor.get(i).getPosition(), listObjectOnFloor.get(i).getSize())) {
				hero.pickobject(listObjectOnFloor.get(i));
				listObjectOnFloor.remove(i);

			}
		}
	}

	// fonctionne pas encore pour les cailloux
	public void collisionEntitiesAndHero() {
		for (int i = 0; i < listEntitiesOnRoom.size(); i++) {
			Vector2 normalizedDirection = new Vector2(hero.getDirection());
			normalizedDirection.euclidianNormalize(hero.getSpeed());
			Vector2 positionAfterMoving =hero.getPosition().addVector(normalizedDirection);
			if (libraries.Physics.rectangleCollision(positionAfterMoving,hero.getSize(),listEntitiesOnRoom.get(i).getPosition(), listEntitiesOnRoom.get(i).getSize())) {
				String id = listEntitiesOnRoom.get(i).getId();
				switch (id) {
				case "rock":
					hero.setDirection(new Vector2());
					break;
				default:
					hero.setCurrentHealth(hero.getCurrentHealth()-1); // too fast
					break;
				}
			}
		}
	}

	public void collisionLarmes_Mur() {
		for (int i = 0; i < larmes_list.size(); i++) {
			Vector2 normalizedDirection = new Vector2(larmes_list.get(i).getDirection());
			normalizedDirection.euclidianNormalize(larmes_list.get(i).getSpeed());
			Vector2 positionAfterMoving = larmes_list.get(i).getPosition().addVector(normalizedDirection);
			if (!libraries.Physics.ZonedeJeu(positionAfterMoving, larmes_list.get(i).getSize())) {
				larmes_list.remove(i);
			}
		}

	}

	public void makeLarmesPlay() {
		if (hero.getTimeLarmes() == 0) {
			hero.setShoot(false);
			hero.setTimeLarmes(resources.HeroInfos.LARMES_FRAME);
		} else if (hero.getShoot()) {
			hero.setTimeLarmes(hero.getTimeLarmes() - 1);
		}
		for (int i = 0; i < larmes_list.size(); i++) {
			larmes_list.get(i).setScope(larmes_list.get(i).getScope() - 1);
			if (larmes_list.get(i).getScope() == 0) {
				larmes_list.remove(i);
			} else {
				larmes_list.get(i).updateGameObject();
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
}
