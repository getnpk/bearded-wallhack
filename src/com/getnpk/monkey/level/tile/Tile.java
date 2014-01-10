package com.getnpk.monkey.level.tile;

import com.getnpk.monkey.graphics.Screen;
import com.getnpk.monkey.graphics.Sprite;

public class Tile {

	public int x, y;
	public Sprite sprite;
	
	
	public Tile(Sprite sprite){
		this.sprite = sprite;
	}
	
	public void render(int x, int y, Screen screen){
		
	}
	
	/**
	 * Avoid or allow blocking on tile
	 * */
	public boolean solid(){
		return false;
	}
}
