package entity.core;

import entity.Player;
import entity.core.constants.Constants;
import entity.core.constants.Sounds;
import entity.object.SuperObject;
import entity.textures.TextureManager;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements Runnable {

    TextureManager textureManager = new TextureManager(this);
    KeyHandler handler = new KeyHandler(this);
    public UI ui = new UI(this);
    SFX sfx = new SFX();
    SFX sound = new SFX();
    Thread gameThread;

    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public AssetSetter assetSetter = new AssetSetter(this);


    public Player player = new Player(this, handler);
    @Getter
    private final List<SuperObject> objectList = new ArrayList<>();
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
        final double TARGET_FPS = 60.0;
        final double TARGET_TIME_PER_FRAME = 1000000000 / TARGET_FPS;

        long lastTime = System.nanoTime();
        double unprocessedTime = 0;

        while (gameThread != null) {
            long startTime = System.nanoTime();
            long elapsedTime = startTime - lastTime;
            lastTime = startTime;
            unprocessedTime += elapsedTime;

            while (unprocessedTime >= TARGET_TIME_PER_FRAME) {
                update();
                unprocessedTime -= TARGET_TIME_PER_FRAME;
            }

            repaint();

            long endTime = System.nanoTime();
            long frameTime = endTime - startTime;

            long sleepTime = (long) (TARGET_TIME_PER_FRAME - frameTime);

            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime / 1000000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
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
        for (SuperObject superObject : getObjectList()) {
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

    public SuperObject getObjectWith(int index) {
        return objectList.get(index);
    }

}
