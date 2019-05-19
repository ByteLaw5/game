package com.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
<<<<<<< HEAD
import java.nio.file.Paths;
import java.util.ArrayList;
=======
>>>>>>> parent of ddd19ba... really buggy code lol pls help
import java.util.Collection;
import java.util.LinkedList;
<<<<<<< HEAD
import java.util.Map;
import java.util.List;
=======

>>>>>>> parent of ddd19ba... really buggy code lol pls help
import com.game.block.BlockType;
import com.game.entity.Player;
import com.game.entity.ZombieEntity;
<<<<<<< HEAD
import com.game.level.Level;
import com.game.level.ReadableSection;
import com.game.level.data.LevelDataLoader;
=======
>>>>>>> parent of ddd19ba... really buggy code lol pls help
import com.game.listeners.KeyListener;
import com.game.listeners.MouseListener;
import com.game.objects.*;
import com.game.objects.Block;
import com.game.util.BufferedImageLoader;
import com.game.util.ID;
import com.game.util.IHasPlace;
import com.game.util.SoundEngine;

public class GameBase extends Canvas implements Runnable {
	private static final long serialVersionUID = -7501386080343782626L;
	/**
	 * Background colour.
	 */
	public static Window FRAME;
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
<<<<<<< HEAD
	/**
	 * A {@link Map} of {@link LinkedList} instances representing objects in each section.
	 * Currently only includes blocks
	 */
	private final Map<Integer, LinkedList<GameObject>> SECTION_OBJECTS = new HashMap<>();
	private final LinkedList<Tuple<Integer, GameObject>> SECTION_ON_WAIT = new LinkedList<>();
	private final LinkedList<Tuple<Integer, GameObject>> SECTION_TO_REMOVE = new LinkedList<>();

	private Level loadedLevel = null;
	private File loadedFile = null;
	private static File staticLoadedFile = null;

	public final List<Object> listeners = new ArrayList<>();

=======
	
>>>>>>> parent of ddd19ba... really buggy code lol pls help
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
<<<<<<< HEAD
		instance = this;
		BlockType _temp = BlockType.AIR;
		System.out.println(_temp);
=======
>>>>>>> parent of ddd19ba... really buggy code lol pls help

		RUN_DIR.mkdirs();

		WIDTH = getWidth();
		HEIGHT = getHeight();
		
		BufferedImageLoader loader = new BufferedImageLoader();
<<<<<<< HEAD

		player = new Player(0, 0, getInstance());

		loadLevel("test");

		se = new SoundEngine();
		se.load();
		cam = new Camera(player.getX(), player.getY());

		for (int sectionX = -4; sectionX <= 4; ++sectionX) {
			loadUnloadSection(sectionX, player);
		}

		player.setY((loadedLevel.getHeightAtPos(0) + 1) * 32);
		System.out.println("Player y: " + player.getY());

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
=======
		level = loader.loadImage("/res/level.png");
		
		instance = this;

		se = new SoundEngine();
		se.load();
		cam = new Camera(0, 0);
		
		addLevel(level);
		
		this.addKeyListener(new KeyListener(player));
>>>>>>> parent of ddd19ba... really buggy code lol pls help

		this.drawMessage((Graphics2D)this.getGraphics(), "", 0, 0);
	}

<<<<<<< HEAD
	private void loadLevel(String levelName)
	{
		loadedFile = Paths.get(RUN_DIR.toString() + "/levels/" + levelName).toFile();
		staticLoadedFile = loadedFile;
		loadedLevel = LevelDataLoader.loadLevel(loadedFile);
	}

	public static File getStaticLoadedFile()
	{
		return staticLoadedFile;
	}

	/**
	 * Loads or unloads a section, if the section should be so updated
	 *
	 * @param sectionX
	 * @param loaders
	 * @return whether the section is now loaded
	 */
	private boolean loadUnloadSection(int sectionX, ISectionLoader...loaders)
	{
		boolean result = false;

		for (ISectionLoader loader : loaders)
		{
			if (loader.shouldSectionLoad(sectionX))
			{
				result = true;
				break;
			}
		}

		if (result && !loadedLevel.isSectionLoaded(sectionX))
		{
			LevelDataLoader.loadSection(loadedLevel, loadedFile, sectionX);
		}
		else
		{
			LevelDataLoader.writeSection(loadedLevel, loadedFile, sectionX);
			loadedLevel.unloadSection(sectionX);
		}

		return result;
	}

	private void updateSectionObjects(int sectionX)
	{
		if (loadedLevel.isSectionLoaded(sectionX))
		{
			ReadableSection section = loadedLevel.getSection(sectionX);
			for (int x = 0; x < 16; ++x)
			{
				for (int y = section.getHeight() - 1; y >= 0; --y)
				{
					BlockType b = section.getBlock(x, y);
					if (b.isVisible())
					{
						addBlock(new Block((x + (16 * sectionX)) * 32, y * 32, getInstance(), b), sectionX);
					}
				}
			}
		}
		else
		{
			if (SECTION_OBJECTS.containsKey(sectionX))
			{
				for (GameObject object : getSectionList(sectionX))
				{
					if (object instanceof Block)
					{
						removeBlock((Block) object, sectionX);
					}
				}
			}
		}
	}

	@Deprecated
=======
	//TODO byte level system (@Valoeghese)
>>>>>>> parent of ddd19ba... really buggy code lol pls help
	private void addLevel(BufferedImage level) {
		int w = level.getWidth();
		int h = level.getHeight();
		
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
					this.addObject(new ZombieEntity(xx * 32, yy * 32, getInstance()));
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
		}
<<<<<<< HEAD
		//Moved cam.tick out of the loop in an attempt to reduce loop lag.
        //If this is a problem feel free to revert it
		cam.tick(player);

		int playerSection = MathUtils.floor(player.getX() / 512F);
		for (int i = playerSection - 6; i <= playerSection + 6; ++i)
		{
			loadUnloadSection(i, player);
			updateSectionObjects(i);
		}

		OBJECTS.addAll(ON_WAIT);
		OBJECTS.removeAll(TO_REMOVE);
		for (Tuple<Integer, GameObject> l : SECTION_ON_WAIT)
		{
			getSectionList(l.getA()).add(l.getB());
		}
		for (Tuple<Integer, GameObject> l : SECTION_TO_REMOVE)
		{
			getSectionList(l.getA()).remove(l.getB());
		}

=======
		OBJECTS.addAll(ON_WAIT);
		OBJECTS.removeAll(TO_REMOVE);
>>>>>>> parent of ddd19ba... really buggy code lol pls help
		ON_WAIT.clear();
		TO_REMOVE.clear();
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
		float _x = cam.getX();
		float _x0 = _x + FRAME.frame.getContentPane().getX();
		float _y = cam.getY();
		float _y0 = _y + FRAME.frame.getContentPane().getY();
		for(GameObject object : OBJECTS) {
			if(object.getBoundingBox().intersects(_x, _y, _x0, _y0)) object.render(g);
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
	
	public static void main(String[] args) {
		FRAME = new Window(900, 600, "Undead Residence Alpha 0.1v", new GameBase());
	}
	
	public static class ObjectDoesNotExistException extends IndexOutOfBoundsException {
		private static final long serialVersionUID = 1L;

		public ObjectDoesNotExistException(String cause) {
			super(cause);
		}
	}

	public <K extends java.awt.event.KeyListener, T> boolean IsKeyListenerInsideList(K listener, Collection<T> coll) {
		return coll.contains(listener);
	}

	public <K extends java.awt.event.MouseAdapter, T> boolean IsMouseListenerInsideList(K listener, Collection<T> coll) {
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
