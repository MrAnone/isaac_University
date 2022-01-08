package gameWorld;

import resources.MonstersInfos;

import java.util.concurrent.ThreadLocalRandom;
import gameobjects.Fly;
import gameobjects.Spider;
import gameobjects.Tears;
import gameobjects.Hero;
import gameobjects.ListObjects;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;
import libraries.StdDraw;
import libraries.Vector2;

public class Room_classic extends Room {

	public Room_classic(Hero hero, String ID) {
		super(hero, ID);
		CreateSpider();
		CreateFly();
		createListEntitiesOnRoom();
		Doorclose();
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
			Spider_list_add(arraignée);
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
				new Vector2(getHero().getPosition().getX() - position.getX(),
						getHero().getPosition().getY() - position.getY()),
				ImagePaths.FLY);
		Fly_list_add(mouche);
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
		for (int i = 0; i < Spider_list_size(); i++) {
			Spider_list_get(i).drawGameObject();
		}
	}

	private void FlyDraw() {
		for (int i = 0; i < Fly_list_size(); i++) {
			Fly_list_get(i).drawGameObject();
			for (int j = 0; j < Fly_list_get(i).Tears_Fly_size(); j++) {
				Fly_list_get(i).Tears_Fly_get(j).drawGameObject();
			}
		}
	}

	@Override
	public void updateRoom() {

		collisionEntitiesAndHero();
		collisionEntitiesAndSpider();
		makeSpiderPlay();
		makeFlyPlay();
		makeHeroPlay();
		makeLarmesPlay();
		collisionLarmes_Mur();
		collisionLarmes_Spiders();
		collisionLarmes_Fly();
		// if (!getHero().getInvincible()) {
		collisionLarmesFly_Isaac();
		collisionSpider_Isaac();
		collisionFly_Isaac();
		// }
		collisionLarmesFly_Mur();
		collisionObjectAndHero();
		roomCleaned();
		makeDoorPlay();
	}

	public void makeDoorPlay() {
		if (Fly_list_size() == 0 && Spider_list_size() == 0) {
			for (int i = 0; i < 4; i++) {
				setDoor_list_Status(i);
			}
		}

	}

	@Override
	public void roomCleaned() {
		if ((Fly_list_size() == 0 && Spider_list_size() == 0) && isRoomAlredayCleared() == false) {
			ListObjects listFullObjects = new ListObjects();
			int randomIndex = ThreadLocalRandom.current().nextInt(0, listFullObjects.size());
			super.getListObjectOnFloor().add(listFullObjects.get(randomIndex));
			setRoomAlredayCleared(true);
		}

	}

	@Override
	public void collisionObjectAndHero() {
		for (int i = 0; i < getListObjectOnFloor().size(); i++) {
			if (libraries.Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(),
					getListObjectOnFloor().get(i).getPosition(), getListObjectOnFloor().get(i).getSize())) {
				getHero().pickobject(getListObjectOnFloor().get(i));
				getListObjectOnFloor().remove(i);
			}
		}
	}

	private void collisionEntitiesAndSpider() {

		for (int i = 0; i < Spider_list_size(); i++) {
			for (int j = 0; j < getListEntitiesOnRoom().size(); j++) {
				Vector2 normalizedDirection = new Vector2(Spider_list_get(i).getDirection());
				normalizedDirection.euclidianNormalize(Spider_list_get(i).getSpeed());
				Vector2 positionAfterMoving = Spider_list_get(i).getPosition().addVector(normalizedDirection);
				if (libraries.Physics.rectangleCollision(positionAfterMoving, Spider_list_get(i).getSize(),
						Spider_list_get(i).getPosition(), Spider_list_get(i).getSize())) {
					String id = getListEntitiesOnRoom().get(i).getId();
					switch (id) {
					case "rock":
						Spider_list_get(i).setDirection(new Vector2());
						break;
					default:
						break;
					}
				}
			}
		}
	}

	private void collisionLarmesFly_Mur() {
		for (int j = 0; j < Fly_list_size(); j++) {
			for (int i = 0; i < Fly_list_get(j).Tears_Fly_size(); i++) {
				Vector2 normalizedDirection = new Vector2(Fly_list_get(j).Tears_Fly_get(i).getDirection());
				normalizedDirection.euclidianNormalize(Fly_list_get(j).Tears_Fly_get(i).getSpeed());
				Vector2 positionAfterMoving = Fly_list_get(j).Tears_Fly_get(i).getPosition()
						.addVector(normalizedDirection);
				if (!libraries.Physics.ZonedeJeu(positionAfterMoving, Fly_list_get(j).Tears_Fly_get(i).getSize())) {
					Fly_list_get(j).Tears_Fly_remove(i);
				}
			}
		}
	}

	private void collisionLarmes_Spiders() {
		for (int i = 0; i < getHero().tears_size(); i++) {
			for (int j = 0; j < Spider_list_size(); j++) {
				if (libraries.Physics.rectangleCollision(getHero().tears_get(i).getPosition(),
						getHero().tears_get(i).getSize(), Spider_list_get(j).getPosition(),
						Spider_list_get(j).getSize())) {
					Spider_list_get(j).setLife(Spider_list_get(j).getLife() - 1);
					getHero().tears_remove(i);
				}
			}
		}
	}

	private void collisionLarmes_Fly() {
		for (int i = 0; i < getHero().tears_size(); i++) {
			for (int j = 0; j < Fly_list_size(); j++) {
				if (libraries.Physics.rectangleCollision(getHero().tears_get(i).getPosition(),
						getHero().tears_get(i).getSize(), Fly_list_get(j).getPosition(), Fly_list_get(j).getSize())) {
					Fly_list_get(j).setLife(Fly_list_get(j).getLife() - 1);
					getHero().tears_remove(i);
				}
			}
		}
	}

	private void collisionLarmesFly_Isaac() {
		for (int i = 0; i < Fly_list_size(); i++) {
			for (int j = 0; j < Fly_list_get(i).Tears_Fly_size(); j++) {
				if (libraries.Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(),
						Fly_list_get(i).Tears_Fly_get(j).getPosition(), Fly_list_get(i).Tears_Fly_get(j).getSize())) {
					if (getHero().isCanBeHurt()) {
						getHero().setCurrentHealth(getHero().getCurrentHealth() - 1);
					}
					Fly_list_get(i).Tears_Fly_remove(j);
				}
			}
		}
	}

	private void collisionFly_Isaac() {
		for (int j = 0; j < Fly_list_size(); j++) {
			if (libraries.Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(),
					Fly_list_get(j).getPosition(), Fly_list_get(j).getSize())) {
				if (getHero().isCanBeHurt()) {
					getHero().setCurrentHealth(getHero().getCurrentHealth() - 1);

				}
				Fly_list_get(j).setDirection(Fly_list_get(j).getDirection().reverse());
			}
		}
	}

	private void collisionSpider_Isaac() {
		for (int j = 0; j < Spider_list_size(); j++) {
			if (libraries.Physics.rectangleCollision(getHero().getPosition(), getHero().getSize(),
					Spider_list_get(j).getPosition(), Spider_list_get(j).getSize())) {
				if (getHero().isCanBeHurt()) {
					getHero().setCurrentHealth(getHero().getCurrentHealth() - 1);

				}
				Spider_list_get(j).setDirection(Spider_list_get(j).getDirection().reverse());
			}
		}
	}

	private void makeSpiderPlay() {
		for (int i = 0; i < Spider_list_size(); i++) {
			Spider_list_get(i).setMove(Spider_list_get(i).getMove() - 1);
			if (Spider_list_get(i).getStop()) {
				if (Spider_list_get(i).getMove() == 0) {
					Spider_list_get(i).setDirection(
							new Vector2(Math.random() * (2.0 - 0.0) - 1.0, Math.random() * (2.0 - 0.0) - 1.0));
					Spider_list_get(i).setMove(20);
					Spider_list_get(i).setStop(false);
				}
			} else {
				if (Spider_list_get(i).getMove() == 0) {
					Spider_list_get(i).setDirection(new Vector2());
					Spider_list_get(i).setMove(5);
					Spider_list_get(i).setStop(true);
				}
			}
			if (Spider_list_get(i).getLife() == 0) {
				Spider_list_remove(i);
			} else {
				Spider_list_get(i).updateGameObject();
			}
		}
	}

	private void makeFlyPlay() {
		for (int i = 0; i < Fly_list_size(); i++) {
			if (Fly_list_get(i).getLife() == 0) {
				Fly_list_remove(i);
			} else {
				Fly_list_get(i)
						.setDirection(new Vector2(getHero().getPosition().getX() - Fly_list_get(i).getPosition().getX(),
								getHero().getPosition().getY() - Fly_list_get(i).getPosition().getY()));
				Fly_list_get(i).updateGameObject();
				makeLarmesFlyPlay(Fly_list_get(i));
			}
		}
	}

	private void makeLarmesFlyPlay(Fly mouche) {
		boolean shoot = createLarmesFly(mouche);
		if (shoot) {
			mouche.setShoot(true);
		}
		makeLarmesPlayFly(mouche);
	}

	private boolean createLarmesFly(Fly mouche) {
		if (mouche.getShoot() == false) {
			Tears shoot = new Tears(mouche.getPosition(), mouche.getDirection(), HeroInfos.LARMES_SIZE,
					MonstersInfos.LARMES_SPEED, MonstersInfos.LARMES_SCOPE, ImagePaths.TEAR_FLY);
			mouche.Tears_Fly_add(shoot);
			return true;
		} else {
			return false;
		}
	}

	private void makeLarmesPlayFly(Fly mouche) {
		if (mouche.getTimeLarmes() == 0) {
			mouche.setShoot(false);
			mouche.setTimeLarmes(MonstersInfos.LARMES_FRAME);
		} else if (mouche.getShoot()) {
			mouche.setTimeLarmes(mouche.getTimeLarmes() - 1);
			;
		}
		for (int i = 0; i < mouche.Tears_Fly_size(); i++) {
			mouche.Tears_Fly_get(i).setScope(mouche.Tears_Fly_get(i).getScope() - 1);
			if (mouche.Tears_Fly_get(i).getScope() == 0) {
				mouche.Tears_Fly_remove(i);
			} else {
				mouche.Tears_Fly_get(i).updateGameObject();
			}
		}
	}

	@Override
	public void drawRoom() {

		StdDraw.picture(0.5, 0.5, ImagePaths.FLOOR_TILE, 1, 1, 0);
		DrawDoor();
		entitiesDraw();
		SpiderDraw();
		FlyDraw();
		Larmes();
		objectsDraw();
		// Afficher();
		getHero().drawGameObject();
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
