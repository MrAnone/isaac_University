package gameobjects;

import libraries.Vector2;


public class Spider extends Monsters {
	
	private int move;
	private boolean stop;
	public Spider(Vector2 position, Vector2 size, double speed, int life, Vector2 direction, String imagePath) {
		super(position, size, speed, life, direction, imagePath);
		this.move = 20;
		this.stop = false;
		// TODO Auto-generated constructor stub
	}
	public void setMove(int move) {
		this.move = move;
	}
	
	public int getMove() {
		return this.move;
	}
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	public boolean getStop() {
		return this.stop;
	}
}
