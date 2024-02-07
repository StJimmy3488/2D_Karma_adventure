package entity.core;

import entity.Player;
import entity.textures.TextureManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen settings
    final int originalTileSize = 16;
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; //48x48 tile

    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenColumn; // 768 px
    public final int screenHeight = tileSize * maxScreenRow; // 576 px

    final int FPS = 60;

    TextureManager textureManager = new TextureManager(this);
    KeyHandler handler = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this, handler);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        this.addKeyListener(handler);
        this.setFocusable(true);
    }

    public void startThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        double drawInterval = (double) 1000000000 /FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;

        while(gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= 1000000000) {
                System.out.println("FPS" + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update() {

        player.update();
    }
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics;
        textureManager.draw(graphics2D);
        player.draw(graphics2D);

        graphics2D.dispose();
    }
}
