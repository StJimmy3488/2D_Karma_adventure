package entity.core;

import entity.Player;
import entity.core.constants.Constants;
import entity.core.constants.Sounds;
import entity.object.SuperObject;
import entity.textures.TextureManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {

    final int FPS = 60;
    TextureManager textureManager = new TextureManager(this);
    KeyHandler handler = new KeyHandler(this);
    public UI ui = new UI(this);
    SFX sfx = new SFX();
    SFX sound = new SFX();
    Thread gameThread;

    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public AssetSetter assetSetter = new AssetSetter(this);


    public Player player = new Player(this, handler);

    public List<SuperObject> obj = new ArrayList<>();
    public GamePanel() {
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        this.setBackground(Color.darkGray);
        this.setDoubleBuffered(true);
        this.addKeyListener(handler);
        this.setFocusable(true);
    }

    public void setupGame() {
        assetSetter.setObject();
        playMusic(Sounds.KARMA_CAT_INTRO);
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

        // TEXTURE
        textureManager.draw(graphics2D);
        //OBJECT
        for (SuperObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(graphics2D, this);
            }
        }
        //PLAYER
        player.draw(graphics2D);

        //UI
        ui.draw(graphics2D);
        graphics2D.dispose();
    }
    public void playMusic(Sounds sounds) {
        sound.setFile(sounds);
        sound.loop();
        sound.play();
    }
    public void stopMusic() {
        sound.stop();
    }
    public void playSFX(Sounds sounds) {
        sfx.setFile(sounds);
        sfx.play();
    }
}
