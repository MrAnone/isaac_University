package gameWorld;

import gameobjects.Hero;
import libraries.StdDraw;
import libraries.Vector2;
import resources.RoomInfos;

public class Room_store extends Room {

	private Hero hero;
	
	public Room_store(Hero hero,String ID) {
		super(hero,ID);
		this.hero = hero;
	}
	
	public void updateRoom()
	{
		makeHeroPlay();
		makeLarmesPlay();
	}
	
	public void  drawRoom () {
		StdDraw.setPenColor(StdDraw.GRAY);
		for (int i = 0; i < RoomInfos.NB_TILES; i++)
		{
			for (int j = 0; j < RoomInfos.NB_TILES; j++)
			{
				Vector2 position = positionFromTileIndex(i, j);
				StdDraw.filledRectangle(position.getX(), position.getY(), RoomInfos.HALF_TILE_SIZE.getX(),
						RoomInfos.HALF_TILE_SIZE.getY());
			}
		}
		hero.drawGameObject();
		Larmes();
		DrawDoor();
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

}
