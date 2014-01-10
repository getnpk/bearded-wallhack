package com.getnpk.monkey.graphics;

public class Sprite {
	
	public int size;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;
	
	public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
	
	public Sprite(int size, int x, int y, SpriteSheet sheet){
		this.size = size;
		this.pixels = new int[size * size];
		
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		
		load();
	}

	/*
	 * Extract single sprite from sprite sheet.
	 * */
	private void load(){
		for (int y = 0; y < size; y++){
			for (int x = 0; x < size; x++){
				pixels[x + y * size] = sheet.pixels[(x + this.x + (y + this.y ) * sheet.size)];
			}
		}
	}
}
