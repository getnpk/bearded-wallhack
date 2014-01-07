package com.getnpk.monkey.graphics;

import java.util.Random;

public class Screen {

	private int width, height;

	public int[] pixels;
	public int[] tiles = new int[64 * 64];
	private Random random = new Random();
	
	public Screen(int width, int height){
		this.width = width;
		this.height = height;
		
		pixels = new int[width * height];
		
		for (int i=0; i < 64*64 ; i++){
			tiles[i] = random.nextInt(0xffffff);
		}
	}
	
	public void clear(){
		for (int i = 0; i < pixels.length; i++){
			pixels[i] = 0;
		}
	}
	
	public void render(int xoffset, int yoffset){
		
		for (int y = 0; y < height; y++){
			int yy = y + yoffset;
			//if (yy < 0 || yy >= height) break;
			for (int x = 0; x < width; x++){
				int xx = x + xoffset;
				//if (xx < 0 || xx >= width) break;
				/*
				 * We want a tile size of 16x16.
				 * Optimistion x >> 4 for x / 16
				 * If xx >= 64, set it back to 0
				 */
				int tileIndex = ((xx >> 4) & 63 ) + ((yy >> 4) & 63 ) * 64;
				pixels[x + y * width] = tiles[tileIndex];
			}
		}
	}
	
}
