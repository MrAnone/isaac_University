package gameWorld;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.RoomInfos;

public class Room_spawn extends Room{
	
	private Hero hero;

	public Room_spawn(Hero hero,String ID) {
		super(hero, ID);
		this.hero = hero;
	}
	
	public void updateRoom()
	{
		makeHeroPlay();
		makeLarmesPlay();
		collisionLarmes_Mur();
	}
	
	public void  drawRoom () {
		StdDraw.picture(0.5, 0.5, ImagePaths.FLOOR_TILE, 1, 1, 0);
		StdDraw.text(0.5, 0.5, "SPAWN");
		hero.drawGameObject();
		Larmes();
		DrawDoor();
		//Afficher();
	}
	
	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	public static Vector2 positionFromTileIndex(int indexX, int indexY)
	{
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}

	@Override
	public void roomCleaned() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void collisionObjectAndHero() {
		// TODO Auto-generated method stub
		
	}

}
