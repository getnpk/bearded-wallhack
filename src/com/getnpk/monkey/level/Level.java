package com.getnpk.monkey.level;

import com.getnpk.monkey.graphics.Screen;

/**
 * Basic Level class 
 * */
public class Level {
	
	private int width, height;
	private int[] tiles;
	
	public Level(int width, int height){
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		
		generateLevel();
	}

	public Level(String path){
		loadLevel(path);
	}
	
	private void loadLevel(String path) {
		
	}

	private void generateLevel() {
		
	}
	
	/**
	 * Update entities movement and AI at 60fps
	 * */
	public void update(){
		
	}

	public void render(int xScroll, int yScorll, Screen screen){
		
	}
	
	private void time(){
		
	}
}
