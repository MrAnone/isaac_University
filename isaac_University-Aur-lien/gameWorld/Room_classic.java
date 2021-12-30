package gameWorld;

import java.util.ArrayList;
import resources.MonstersInfos;

import java.util.concurrent.ThreadLocalRandom;
import gameobjects.Fly;
import gameobjects.Spider;
import gameobjects.Hero;
import gameobjects.Larmes;
import gameobjects.ListObjects;
import resources.ImagePaths;
import resources.RoomInfos;
import libraries.StdDraw;
import libraries.Vector2;

public class Room_classic extends Room {

	private Hero hero;
	private ArrayList<Fly> Fly_list;
	private ArrayList<Spider> Spider_list;
	

	public Room_classic(Hero hero) {
		super(hero);
		this.hero = hero;
		Fly_list = new ArrayList<Fly>();
		Spider_list = new ArrayList<Spider>();
		CreateSpider();
		CreateFly();
		createListEntitiesOnRoom();
	}


	private void CreateSpider() {
		for (int i = 0; i < 1; i++) {
			Spider arraignée = new Spider(
					new Vector2(
							Math.random() * ((1.0 - (resources.MonstersInfos.FLY_SIZE.getX()))
									- (0.0 + (resources.MonstersInfos.FLY_SIZE.getX()))),
							Math.random()
									* ((1.0 - (resources.MonstersInfos.FLY_SIZE.getY()))
											- (0.0 + (resources.MonstersInfos.FLY_SIZE.getY())))
									- (resources.MonstersInfos.FLY_SIZE.getY() / 2)),
					MonstersInfos.SPIDER_SIZE, MonstersInfos.SPIDER_SPEED, MonstersInfos.SPIDER_LIFE,
					new Vector2(Math.random() * (2.0 - 0.0) - 1.0, Math.random() * (2.0 - 0.0) - 1.0),
					ImagePaths.SPIDER);
			Spider_list.add(arraignée);
		}
	}

	private void CreateFly() {
		Vector2 position = new Vector2(
				Math.random() * ((1.0 - (resources.MonstersInfos.FLY_SIZE.getX()))
						- (0.0 + (resources.MonstersInfos.FLY_SIZE.getX()))),
				Math.random()
						* ((1.0 - (resources.MonstersInfos.FLY_SIZE.getY()))
								- (0.0 + (resources.MonstersInfos.FLY_SIZE.getY())))
						- (resources.MonstersInfos.FLY_SIZE.getY() / 2));
		Fly mouche = new Fly(position, MonstersInfos.FLY_SIZE, MonstersInfos.FLY_SPEED, MonstersInfos.FLY_LIFE,
				new Vector2(hero.getPosition().getX() - position.getX(), hero.getPosition().getY() - position.getY()),
				ImagePaths.FLY);
		Fly_list.add(mouche);
	}

	private void objectsDraw() {
		for (int i = 0; i < super.getListObjectOnFloor().size(); i++) {
			super.getListObjectOnFloor().get(i).drawGameObject();
		}
	}
	
	private void entitiesDraw() {
		for (int i = 0; i < super.getListEntitiesOnRoom().size(); i++) {
			super.getListEntitiesOnRoom().get(i).drawGameObject();
		}
	}

	private void SpiderDraw() {
		for (int i = 0; i < Spider_list.size(); i++) {
			Spider_list.get(i).drawGameObject();
		}
	}

	private void FlyDraw() {
		for (int i = 0; i < Fly_list.size(); i++) {
			Fly_list.get(i).drawGameObject();
			for (int j = 0; j < Fly_list.get(i).larmesFly_list.size(); j++) {
				Fly_list.get(i).larmesFly_list.get(j).drawGameObject();
			}
		}
	}

	@Override
	public void updateRoom() {

		collisionEntitiesAndHero();
		makeSpiderPlay();
		makeFlyPlay();	
		makeHeroPlay();
		makeLarmesPlay();		
		collisionLarmes_Mur();
		collisionLarmes_Spiders();
		collisionLarmes_Fly();
		collisionLarmesFly_Isaac();
		collisionSpider_Isaac();
		collisionFly_Isaac();
		collisionLarmesFly_Mur();
		collisionObjectAndHero();
		roomCleaned();
	}

	
	
	@Override
	public void roomCleaned() {
		if((Fly_list.size()==0 && Spider_list.size()==0) && isRoomAlredayCleared()==false ) {
			ListObjects listFullObjects = new ListObjects();
			int randomIndex = ThreadLocalRandom.current().nextInt(0, listFullObjects.size());
			super.getListObjectOnFloor().add(listFullObjects.get(randomIndex));
			setRoomAlredayCleared(true);
		}
		
	}


	private void collisionLarmesFly_Mur() {
		for (int j = 0; j < Fly_list.size(); j++) {
			for (int i = 0; i < Fly_list.get(j).larmesFly_list.size(); i++) {
				Vector2 normalizedDirection = new Vector2(Fly_list.get(j).larmesFly_list.get(i).getDirection());
				normalizedDirection.euclidianNormalize(Fly_list.get(j).larmesFly_list.get(i).getSpeed());
				Vector2 positionAfterMoving = Fly_list.get(j).larmesFly_list.get(i).getPosition()
						.addVector(normalizedDirection);
				if (!libraries.Physics.ZonedeJeu(positionAfterMoving,
						Fly_list.get(j).larmesFly_list.get(i).getSize())) {
					Fly_list.get(j).larmesFly_list.remove(i);
				}
			}
		}

	}

	private void collisionLarmes_Spiders() {
		for (int i = 0; i < larmes_list.size(); i++) {
			for (int j = 0; j < Spider_list.size(); j++) {
				if (libraries.Physics.rectangleCollision(larmes_list.get(i).getPosition(), larmes_list.get(i).getSize(),
						Spider_list.get(j).getPosition(), Spider_list.get(j).getSize())) {
					Spider_list.get(j).setLife(Spider_list.get(j).getLife() - 1);
					larmes_list.remove(i);
				}
			}
		}
	}

	private void collisionLarmes_Fly() {
		for (int i = 0; i < larmes_list.size(); i++) {
			for (int j = 0; j < Fly_list.size(); j++) {
				if (libraries.Physics.rectangleCollision(larmes_list.get(i).getPosition(), larmes_list.get(i).getSize(),
						Fly_list.get(j).getPosition(), Fly_list.get(j).getSize())) {
					Fly_list.get(j).setLife(Fly_list.get(j).getLife() - 1);
					larmes_list.remove(i);
				}
			}
		}
	}

	private void collisionLarmesFly_Isaac() {
		for (int i = 0; i < Fly_list.size(); i++) {
			for (int j = 0; j < Fly_list.get(i).larmesFly_list.size(); j++) {
				if (libraries.Physics.rectangleCollision(hero.getPosition(), hero.getSize(),
						Fly_list.get(i).larmesFly_list.get(j).getPosition(),
						Fly_list.get(i).larmesFly_list.get(j).getSize())) {
					hero.setCurrentHealth(hero.getCurrentHealth() - 1);
					Fly_list.get(i).larmesFly_list.remove(j);
				}
			}
		}

	}

	private void collisionFly_Isaac() {
		for (int j = 0; j < Fly_list.size(); j++) {
			if (libraries.Physics.rectangleCollision(hero.getPosition(), hero.getSize(), Fly_list.get(j).getPosition(),
					Fly_list.get(j).getSize())) {
				hero.setCurrentHealth(hero.getCurrentHealth() - 1);
				Fly_list.get(j).setDirection(Fly_list.get(j).getDirection().reverse());
			}
		}
	}

	private void collisionSpider_Isaac() {
		for (int j = 0; j < Spider_list.size(); j++) {
			if (libraries.Physics.rectangleCollision(hero.getPosition(), hero.getSize(),
					Spider_list.get(j).getPosition(), Spider_list.get(j).getSize())) {
				hero.setCurrentHealth(hero.getCurrentHealth() - 1);
				Spider_list.get(j).setDirection(Spider_list.get(j).getDirection().reverse());
			}
		}
	}

	private void makeSpiderPlay() {
		for (int i = 0; i < Spider_list.size(); i++) {
			Spider_list.get(i).setMove(Spider_list.get(i).getMove() - 1);
			if (Spider_list.get(i).getStop()) {
				if (Spider_list.get(i).getMove() == 0) {
					Spider_list.get(i).setDirection(
							new Vector2(Math.random() * (2.0 - 0.0) - 1.0, Math.random() * (2.0 - 0.0) - 1.0));
					Spider_list.get(i).setMove(20);
					Spider_list.get(i).setStop(false);
				}
			} else {
				if (Spider_list.get(i).getMove() == 0) {
					Spider_list.get(i).setDirection(new Vector2());
					Spider_list.get(i).setMove(5);
					Spider_list.get(i).setStop(true);
				}
			}
			if (Spider_list.get(i).getLife() == 0) {
				Spider_list.remove(i);
			} else {
				Spider_list.get(i).updateGameObject();
			}
		}
	}

	private void makeFlyPlay() {
		for (int i = 0; i < Fly_list.size(); i++) {
			if (Fly_list.get(i).getLife() == 0) {
				Fly_list.remove(i);
			} else {
				Fly_list.get(i)
						.setDirection(new Vector2(hero.getPosition().getX() - Fly_list.get(i).getPosition().getX(),
								hero.getPosition().getY() - Fly_list.get(i).getPosition().getY()));
				Fly_list.get(i).updateGameObject();
				makeLarmesFlyPlay(Fly_list.get(i));
			}
		}
	}

	private void makeLarmesFlyPlay(Fly mouche) {
		boolean shoot = Larmes.createLarmesFly(mouche.getPosition(), mouche.getDirection(), mouche.larmesFly_list,
				mouche.getShoot());
		if (shoot) {
			mouche.setShoot(true);
		}
		makeLarmesPlayFly(mouche);
	}

	private void makeLarmesPlayFly(Fly mouche) {
		if (mouche.getTimeLarmes() == 0) {
			mouche.setShoot(false);
			mouche.setTimeLarmes(MonstersInfos.LARMES_FRAME);
		} else if (mouche.getShoot()) {
			mouche.setTimeLarmes(mouche.getTimeLarmes() - 1);
			;
		}
		for (int i = 0; i < mouche.larmesFly_list.size(); i++) {
			mouche.larmesFly_list.get(i).setScope(mouche.larmesFly_list.get(i).getScope() - 1);
			if (mouche.larmesFly_list.get(i).getScope() == 0) {
				mouche.larmesFly_list.remove(i);
			} else {
				mouche.larmesFly_list.get(i).updateGameObject();
			}
		}
	}

	private void Afficher() {
		StdDraw.picture(0.929, 0.5, ImagePaths.CLOSED_DOOR, 0.2, 0.15, -90.0);
		StdDraw.picture(0.071, 0.5, ImagePaths.CLOSED_DOOR, 0.2, 0.15, 90.0);
		StdDraw.picture(0.5, 0.071, ImagePaths.CLOSED_DOOR, 0.2, 0.15, 180.0);
		StdDraw.picture(0.5, 0.929, ImagePaths.OPENED_DOOR, 0.2, 0.15, 0.0);
	}

	@Override
	public void drawRoom() {
		//StdDraw.setPenColor(StdDraw.GRAY);
		StdDraw.picture(0.5, 0.5, ImagePaths.FLOOR_TILE, 1, 1, 0);
		/*for (int i = 0; i < RoomInfos.NB_TILES; i++) {
			for (int j = 0; j < RoomInfos.NB_TILES; j++) {
				Vector2 position = positionFromTileIndex(i, j);
				StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
						RoomInfos.HALF_TILE_SIZE.getY());
				StdDraw.picture(position.getX(), position.getY(), ImagePaths.FLOOR_TILE);
				
			}
		}*/
		entitiesDraw();
		hero.drawGameObject();
		SpiderDraw();
		FlyDraw();
		Larmes();
		objectsDraw();
		Afficher();
	}

	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	public static Vector2 positionFromTileIndex(int indexX, int indexY) {
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}
}
