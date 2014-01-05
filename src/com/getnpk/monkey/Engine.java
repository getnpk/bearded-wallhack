package com.getnpk.monkey;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import com.getnpk.monkey.graphics.Screen;

public class Engine extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	//fixing aspect ratio
	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;

	public static String title = "ENGINE";
	
	private Thread thread;
	private JFrame frame;

	private boolean running = false;

	private Screen screen;

	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	public Engine() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
	}

	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		
		//we need 60 times a second
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			while(delta >= 1){
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			//do this every second
			if (System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println(updates + " UPS, " + frames + " FPS");
				frame.setTitle(Engine.title + " | " + updates + " UPS, " + frames + " FPS");
				updates = 0;
				frames = 0;
			}
		}
		
		stop();
	}

	public void update() {
	}

	/*
	 * To be called unlimited times. 
	 * Renders frame
	 */
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			//try for speed imp, using 3 buffers
			createBufferStrategy(3);
			return;
		}

		//clear screen before rendering
		screen.clear();
		screen.render();

		for (int i = 0; i < pixels.length; i++){
			pixels[i] = screen.pixels[i];
		}
		
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		//remove after every render
		g.dispose();

		//make the next available buffer visible
		bs.show();
	}

	public static void main(String[] args) {
		Engine engine = new Engine();
		engine.frame.setResizable(false);
		engine.frame.setTitle(Engine.title);
		engine.frame.add(engine);
		engine.frame.pack();
		engine.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		engine.frame.setLocationRelativeTo(null);
		engine.frame.setVisible(true);

		engine.start();
	}
}



