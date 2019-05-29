package com.game;

import com.game.block.BlockType;
import com.game.entity.Player;
import com.game.entity.ZombieEntity;
import com.game.item.ItemStack;
import com.game.item.StickItem;
import com.game.item.WorldItem;
import com.game.listeners.KeyListener;
import com.game.listeners.MouseListener;
import com.game.objects.Block;
import com.game.objects.GameObject;
import com.game.util.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class GameBase extends Canvas implements Runnable {
	private static final long serialVersionUID = -7501386080343782626L;
	/**
	 * Background colour.
	 */
	public static final Color BACKGROUND = new Color(153, 204, 255);
	/**
	 * A complete {@link LinkedList} that contains ALL {@link GameObject}s (That implement {@link IHasPlace})
	 */
	public static final LinkedList<GameObject> OBJECTS = new LinkedList<>();
	/**
	 * A {@link LinkedList} of objects that are waiting to be added.
	 */
	public static final LinkedList<GameObject> ON_WAIT = new LinkedList<>();
	/**
	 * A {@link LinkedList} of objects that are waiting to be removed.
	 */
	public static final LinkedList<GameObject> TO_REMOVE = new LinkedList<>();

	public final List<Object> listeners = new ArrayList<>();
	
	private GameBase instance;
	private Player player;
	
	private BufferedImage level = null;
	
	private boolean running = false;
	private Thread thread;
	private SoundEngine se;

	private KeyListener keyListener;
	private MouseListener mouseListener;
	
	private Camera cam;
	
	public static int WIDTH, HEIGHT;

	public static final File RUN_DIR = new File("./run/");

	private void init() {

		RUN_DIR.mkdirs();

		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		BufferedImageLoader loader = new BufferedImageLoader();
		level = loader.loadImage("/res/level.png");
		
		instance = this;

		se = new SoundEngine();
		se.load();
		cam = new Camera(0, 0);
		
		addLevel(level);

		keyListener = new KeyListener(player);
		mouseListener = new MouseListener(player);

		this.addKeyListener(keyListener);
		this.addMouseListener(mouseListener);

		if(IsKeyListenerInsideList(keyListener, listeners) && IsMouseListenerInsideList(mouseListener, listeners)) {
			for(Object listener : listeners) {
				if(listener.getClass().getAnnotation(IsListener.class) == null) {
					throw new UnregisteredListener("Found unregistered listener with name: " + listener.toString());
				}
			}
		} else {
			throw new UnregisteredListener("The key/mouse or both listeners weren't in the list");
		}

		this.drawMessage((Graphics2D)this.getGraphics(), "", 0, 0);
	}

	//TODO byte level system (@Valoeghese)
	private void addLevel(BufferedImage level) {
		int w = level.getWidth();
		int h = level.getHeight();

		addObject(new WorldItem(new ItemStack(new StickItem(), 3), 1000, 100, this));
		
		for(int xx = 0; xx < h; xx++) {
			for(int yy = 0; yy < w; yy++) {
				int pixel = level.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff, green = (pixel >> 8) & 0xff, blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue == 255) {
					block(new Block(xx * 32, yy * 32, getInstance(), BlockType.EARTH));
				}
				if(red == 0 && green == 0 && blue == 255) {
					Player player = new Player(xx * 32, yy * 32, getInstance());
					this.player = player;
					this.addObject(player);
				}
				if(red == 255 && green == 0 && blue == 0) {
					//this.addObject(new ZombieEntity(xx * 32, yy * 32, getInstance()));
				}
				/*if(red == 255 && green == 0 && blue == 100) {
					this.addObject(new ShootingCommonEntity(xx * 32, yy * 32, getInstance()));
				}
				if(red == 255 && green == 100 && blue == 0) {
					this.addObject(new JumpingCommonEntity(xx * 32, yy * 32, getInstance()));
				}*/
				if(red == 0 && green == 255 && blue == 0) block(new Block(xx * 32, yy * 32, getInstance(), BlockType.EARTH_BACKGROUND));
			}
		}
	}
	
	public synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	@Override
	public void run() {
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			
			render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	private void tick() {
		for(GameObject object : OBJECTS) {
			object.tick();
			object.ticks++;
			cam.tick(player);
			if(object instanceof ICollision) {
				((ICollision)object).checkCollisions();
			}
		}
		OBJECTS.addAll(ON_WAIT);
		OBJECTS.removeAll(TO_REMOVE);
		ON_WAIT.clear();
		TO_REMOVE.clear();
		WIDTH = getWidth();
		HEIGHT = getHeight();
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D)g;
		
		g.setColor(BACKGROUND);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2d.translate(cam.getX(), cam.getY());
		for(GameObject object : OBJECTS) {
			object.render(g);
		}
		g2d.translate(-cam.getX(), -cam.getY());
		
		g.dispose();
		bs.show();
	}
	
	public void addObject(GameObject object) {
		ON_WAIT.add(object);
	}
	
	public void addObject(int index, float x, float y) {
		if(index == ID.Player.getId()) {
			this.addObject(new Player(x, y, getInstance()));
		} else if(index == ID.Block.getId()) {
			this.addObject(new Block(x, y, getInstance(), BlockType.EARTH));
		} else if(index == ID.Enemy.getId()) {
			this.addObject(new ZombieEntity(x, y, getInstance()));
		}
	}
	
	public void addObjects(Collection<? extends GameObject> block) {
		ON_WAIT.addAll(block);
	}
	
	public void block(Block block) {
		ON_WAIT.add(block);
	}
	
	public void removeObject(GameObject object) throws ObjectDoesNotExistException {
		if(OBJECTS.contains(object)) {
			TO_REMOVE.add(object);
		} else {
			throw new ObjectDoesNotExistException("The requested object to be removed does not exist.");
		}
	}
	
	public void removeObject(int index) throws ObjectDoesNotExistException {
		if(OBJECTS.get(index) != null) {
			OBJECTS.remove(index);
		} else {
			throw new ObjectDoesNotExistException("The requested object to be removed does not exist.");
		}
	}
	
	public void drawMessage(Graphics2D g2d, Object message, Font font, Color color, int x, int y) {
		g2d.setFont(font);
		g2d.setColor(color);
		g2d.drawString(message.toString(), x, y);
	}
	
	public void drawMessage(Graphics2D g2d, Object message, int x, int y) {
		this.drawMessage(g2d, message, new Font("Purisa", Font.PLAIN, 25), Color.BLACK, x, y);
	}
	
	public GameBase getInstance() {
		return instance;
	}

	public SoundEngine getSoundEngine() {
		return se;
	}
	
	public Player getPlayer() {
		return player;
	}

	public static Window window;
	public static void main(String[] args) {
		window = new Window("Unusual world", new GameBase());
	}
	
	public static class ObjectDoesNotExistException extends IndexOutOfBoundsException {
		private static final long serialVersionUID = 1L;

		public ObjectDoesNotExistException(String cause) {
			super(cause);
		}
	}

	private <K extends java.awt.event.KeyListener, T> boolean IsKeyListenerInsideList(K listener, Collection<T> coll) {
		return coll.contains(listener);
	}

	private <K extends java.awt.event.MouseAdapter, T> boolean IsMouseListenerInsideList(K listener, Collection<T> coll) {
		return coll.contains(listener);
	}

	public static class UnregisteredListener extends RuntimeException {
		private static final long serialVersionUID = 1L;

		public UnregisteredListener() {
			super();
		}

		public UnregisteredListener(String cause) {
			super(cause);
		}
	}
}
