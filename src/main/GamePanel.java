package main;

import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // Screen Settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    public final int tileSize = originalTileSize * scale; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // World Settings
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    final int FPS = 60;

    Thread gameThread;
    KeyHandler keyHandler = new KeyHandler();
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyHandler);
    TileManager tileManager = new TileManager(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));  // set the size of this class(JPanel)
        this.setBackground(Color.DARK_GRAY);
        //Improves Game Efficiency⬇⬇
        this.setDoubleBuffered(true); // Is set true, all drawings from this component will be done in an offscreen painting buffer
        this.addKeyListener(keyHandler);
        this.setFocusable(true); // With this, this GamePanel can be  "focused" to receive key input.
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / FPS; // 0.016666 seconds
        double delta = 0;
        long lastTime = System.nanoTime(); // Returns the current value of the running JVMs high-resolution time source in nanoseconds
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();  // This is the way to call paintComponent
                delta--;
                drawCount++;
            }

            if (timer >= 1000000000) {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {
        player.update();
    }

    // Graphics -> A class that has many functions to draw objects on screen.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Graphics2D class extends the Graphics class to provide more sophisticated control over geometry, coordinate transformations,
        // color management, and text layout.
        Graphics2D g2 = (Graphics2D) g;

        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}
