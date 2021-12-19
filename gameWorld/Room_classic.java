package gameWorld;

import java.util.ArrayList;
import resources.MonstersInfos;

import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Monsters;
import resources.HeroInfos;
import resources.ImagePaths;
import resources.RoomInfos;
import libraries.StdDraw;
import libraries.Vector2;

public class Room_classic extends Room{
	
	private Hero hero;
	
	public Room_classic(Hero hero) {
		super(hero);
		this.hero = hero;
	}
	
	public void Afficher() {
		StdDraw.picture(0.929,0.5,ImagePaths.CLOSED_DOOR,0.2,0.15,90.0);
		StdDraw.picture(0.071,0.5,ImagePaths.CLOSED_DOOR,0.2,0.15,-90.0);
		StdDraw.picture(0.5,0.071,ImagePaths.CLOSED_DOOR,0.2,0.15,0.0);
		StdDraw.picture(0.5,0.929,ImagePaths.OPENED_DOOR,0.2,0.15,180.0);
	}
	@Override
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
		mouche.drawGameObject();
		for(int i=0;i<larmes_list.size();i++) {
			larmes_list.get(i).drawGameObject();
			
		}
		Afficher();
	}
	private static Vector2 positionFromTileIndex(int indexX, int indexY)
	{
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}
}
