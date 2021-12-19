package gameWorld;

import java.util.ArrayList;

import gameobjects.Fly;
import gameobjects.Hero;
import gameobjects.Larmes;
import gameobjects.Monsters;
import libraries.StdDraw;
import libraries.Vector2;
import resources.ImagePaths;
import resources.MonstersInfos;
import resources.RoomInfos;
import gameWorld.Room_classic;

public abstract class Room
{
	private Hero hero;
	private ArrayList<Monsters> monstres_list;
	public static ArrayList<Larmes> larmes_list;
	Fly mouche = new Fly(RoomInfos.POSITION_CENTER_OF_ROOM, MonstersInfos.FLY_SIZE, MonstersInfos.FLY_SPEED,ImagePaths.FLY);


	public Room(Hero hero)
	{
		this.hero = hero;
		this.monstres_list = new ArrayList <Monsters>();
		this.larmes_list = new ArrayList <Larmes>();

		
	}


	/*
	 * Make every entity that compose a room process one step
	 */
	public void updateRoom()
	{
		makeHeroPlay();
		makeFlyPlay();
		makeLarmesPlay();
		
	}
	private void makeLarmesPlay()
	{
		for(int i=0;i<larmes_list.size();i++) {
			larmes_list.get(i).updateGameObject();
			
		}
	}

	private void makeHeroPlay()
	{
		hero.updateGameObject();
	}
	
	private void makeFlyPlay()
	{
		mouche.updateGameObject();	
	}

	/*
	 * Drawing
	 */
	public void drawRoom()
	{
		
	}
	
	public static void afficherLarmes(Larmes shoot){
		larmes_list.add(shoot);
	}
	
	/**
	 * Convert a tile index to a 0-1 position.
	 * 
	 * @param indexX
	 * @param indexY
	 * @return
	 */
	private static Vector2 positionFromTileIndex(int indexX, int indexY)
	{
		return new Vector2(indexX * RoomInfos.TILE_WIDTH + RoomInfos.HALF_TILE_SIZE.getX(),
				indexY * RoomInfos.TILE_HEIGHT + RoomInfos.HALF_TILE_SIZE.getY());
	}
}
