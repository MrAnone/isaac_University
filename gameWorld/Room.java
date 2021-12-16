package gameWorld;

import java.util.ArrayList;
import java.util.List;

import gameobjects.Consumable_Objects;
import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Room {
	private Hero hero;
	private List<Consumable_Objects> listObjects;

	public Room(Hero hero) {
		this.hero = hero;
		this.listObjects = new ArrayList<Consumable_Objects>();
		Consumable_Objects fullHeart= new Consumable_Objects(2,ImagePaths.HEART_PICKABLE);
		Consumable_Objects halfHeart= new Consumable_Objects(1,ImagePaths.HALF_HEART_PICKABLE);
		Consumable_Objects penny= new Consumable_Objects(1,ImagePaths.COIN);
		Consumable_Objects nickel= new Consumable_Objects(5,ImagePaths.NICKEL);
		Consumable_Objects dime= new Consumable_Objects(10,ImagePaths.DIME);
		listObjects.add(fullHeart);
		listObjects.add(halfHeart);
		listObjects.add(penny);
		listObjects.add(nickel);
		listObjects.add(dime);	
	}

	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom() {
		makeHeroPlay();
	}

	private void makeHeroPlay() {
		hero.updateGameObject();
	}

	/*
	 * Drawing
	 */
	public void drawRoom() {
		// For every tile, set background color.
		StdDraw.setPenColor(StdDraw.GRAY);
		for (int i = 0; i < RoomInfos.NB_TILES; i++) {
			for (int j = 0; j < RoomInfos.NB_TILES; j++) {
				Vector2 position = positionFromTileIndex(i, j);
				StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
						RoomInfos.HALF_TILE_SIZE.getY());
			}
		}
		hero.drawGameObject();
		listObjects.get(2).drawGameObject();
	}

	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	private static Vector2 positionFromTileIndex(int indexX, int indexY) {
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}

	/*
	 * Getters and Setters
	 */
	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}

	public List<Consumable_Objects> getListObjects() {
		return listObjects;
	}

	public void setListObjects(List<Consumable_Objects> listObjects) {
		this.listObjects = listObjects;
	}

}
