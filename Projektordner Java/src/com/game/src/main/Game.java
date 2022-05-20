package com.game.src.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

/**
 * class with main game logic
 * 
 * @author Dominik Huerrig
 * @version 1.0
 *
 */
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	// width of window
	public static final int WIDTH = 320;
	// height of window
	public static final int HEIGHT = WIDTH / 12 * 9;
	// scale of window
	public static final int SCALE = 2;
	// title of window
	public final String TITLE = "Brick Breaker";

	public int brickCount = 0;

	private boolean running = false;
	private boolean pause = true;
	private Image img;
	private int totalBricks = 0;

	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);

	private Player p;
	private Ball b;
	private MapGenerator map;
	private Menu menu;

	// game states
	public static enum STATE {
		MENU, GAME, PAUSE, LEVEL, LOSE, WON, OPTION, STOP, RESTART
	};

	// standard game state
	public static STATE State = STATE.MENU;

	// game level
	public static enum LEVEL {
		ONE, TWO, TREE, FOUR, FIVE, SIX, SEVEN, EIGHT
	};

	// standard level
	public static LEVEL Level = LEVEL.ONE;

	/**
	 * contains to initialize objects
	 * 	
	 *
	 */
	public void init() {
		BufferedImageLoader loader = new BufferedImageLoader();

		// listen for key inputs
		this.addKeyListener(new KeyInput(this));
		// listen for mouse inputs
		this.addMouseListener(new MouseInput());

		p = new Player();
		b = new Ball();
		getMap();
		menu = new Menu();
	}

	/**
	 * starts game loop
	 * 	
	 *
	 */
	private synchronized void start() {
		if (running) {
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}

	/**
	 * stops game loop
	 * 	
	 *
	 */
	public synchronized void stop() {
		if (!running) {
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}

	/**
	 * resets game objects
	 * 	
	 *
	 */
	public synchronized void reset() {
		pause = true;
		p = new Player();
		b = new Ball();
		getMap();

	}

	/**
	 * selects map from selected level
	 * 	
	 * @return map returns current game map
	 *
	 */
	public MapGenerator getMap() {
		if (Level == LEVEL.ONE) {
			map = new MapGenerator(2, 4);
			totalBricks = 8;
		} else if (Level == LEVEL.TWO) {
			map = new MapGenerator(3, 5);
			totalBricks = 15;
		} else if (Level == LEVEL.TREE) {
			map = new MapGenerator(3, 7);
			totalBricks = 21;
		} else if (Level == LEVEL.FOUR) {
			map = new MapGenerator(4, 7);
			totalBricks = 28;
		} else if (Level == LEVEL.FIVE) {
			map = new MapGenerator(4, 10);
			totalBricks = 40;
		} else if (Level == LEVEL.SIX) {
			map = new MapGenerator(5, 10);
			totalBricks = 50;
		} else if (Level == LEVEL.SEVEN) {
			map = new MapGenerator(5, 12);
			totalBricks = 60;
		} else if (Level == LEVEL.EIGHT) {
			map = new MapGenerator(6, 12);
			totalBricks = 72;
		}
		return map;

	}

	/**
	 * contains to game loop
	 * 	
	 *
	 */
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			try {
				render();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " Ticks, Fps " + frames);
				updates = 0;
				frames = 0;
			}

		}
		stop();

	}

	/**
	 * updates position of objects
	 * contains collision logic	 * 	
	 *
	 */
	private void tick() {
		if (State == STATE.GAME && !pause) {
			// logic for collision of ball with board
			if (new Rectangle(b.getX(), b.getY(), b.BALL_RADIUS, b.BALL_RADIUS)
					.intersects(new Rectangle((int) p.getX(), (int) p.getY(), p.PLAYER_WIDTH, p.PLAYER_HEIGHT))) {
				b.setVelY(-b.getVelY());
			}

			// logic for collision of ball with brick
			A: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j * map.brickWidth + 30;
						int brickY = i * map.brickHeight + 20;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(b.getX(), b.getY(), b.BALL_RADIUS, b.BALL_RADIUS);
						Rectangle brickRect = rect;

						if (ballRect.intersects(brickRect)) {
							map.setBrickValue(0, i, j);
							totalBricks--;
							if (b.getX() + b.BALL_RADIUS - 1 <= brickRect.x
									|| b.getX() + 1 >= brickRect.x + brickRect.width) {
								b.setVelX(-b.getVelX());
							} else {
								b.setVelY(-b.getVelY());
							}

							break A;
						}
					}
				}
			}

			p.tick();
			b.tick();
			if (totalBricks <= 0) {
				State = STATE.WON;
			}
		}
	}

	/**
	 * render game elements + background
	 * 	
	 * @throws IOException throws exception when image couldn't be loaded
	 *
	 */
	private void render() throws IOException {
		BufferStrategy bs = this.getBufferStrategy();

		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();
		if (img == null) {
			img = ImageIO.read(new File("res/background.png"));
		}
		g.drawImage(img, 0, 0, null);

		// g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		if (State == STATE.GAME) {
			p.render(g);
			b.render(g);
			map.render((Graphics2D) g);
		} else if (State == STATE.MENU || State == STATE.LEVEL || State == STATE.OPTION || State == STATE.PAUSE
				|| State == STATE.WON || State == STATE.LOSE) {
			try {
				menu.render(g);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (State == STATE.STOP) {
			System.exit(1);
		} else if (State == STATE.RESTART) {
			reset();
			State = STATE.GAME;
		}

		g.dispose();
		bs.show();

	}

	/**
	 * event when key is pressed
	 * 
	 * @param e event when key is pressed
	 *
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (State == STATE.GAME) {
			if (key == KeyEvent.VK_RIGHT) {
				p.setVelX(5);

			} else if (key == KeyEvent.VK_LEFT) {
				p.setVelX(-5);
			} else if (key == KeyEvent.VK_ESCAPE) {
				pause = true;
				State = STATE.PAUSE;

			} else if (key == KeyEvent.VK_SPACE) {
				pause = false;
			}
		}
	}

	/**
	 * event when key is released
	 * 
	 * @param e event when key is released
	 *
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_RIGHT) {
			p.setVelX(0);

		} else if (key == KeyEvent.VK_LEFT) {
			p.setVelX(0);
		} else if (key == KeyEvent.VK_ENTER) {

		} else if (key == KeyEvent.VK_SPACE) {

		}
	}

	/**
	 * program window gets set and game started
	 * 
	 * @param args start arguments
	 *
	 */
	public static void main(String args[]) {
		Game game = new Game();

		// set dimensions of window
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		ImageIcon iconImage = new ImageIcon("res/icon.png");

		// set title of window
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setIconImage(iconImage.getImage());
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}

}
