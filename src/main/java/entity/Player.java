package entity;

import entity.core.constants.Constants;
import entity.core.GamePanel;
import entity.core.KeyHandler;
import entity.core.constants.Direction;

import java.awt.*;
import java.awt.image.BufferedImage;

import static entity.core.constants.Direction.*;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    private BufferedImage[] sprites;
    private int spriteIndex = 0;
    private final int animationDelay = 16;
    private int animationCounter = 0;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        screenX = Constants.SCREEN_WIDTH/2 - (Constants.TILE_SIZE/2);
        screenY = Constants.SCREEN_HEIGHT/2 - (Constants.TILE_SIZE/2);
        solidArea = new Rectangle(8, 8, 24, 24);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        initializePlayer();
        this.loadSprites();
    }
    private void loadSprites() {
        BufferedImage[] sprites = direction.getSprites();
        if (sprites != null && sprites.length > 0) {
            this.sprites = sprites;
        } else {
            throw new IllegalArgumentException("No sprites found for direction: " + direction);
        }
    }

    private Direction getDirection() {
        if (keyHandler.upPressed) {
            return UP;
        } else if (keyHandler.downPressed) {
            return DOWN;
        } else if (keyHandler.leftPressed) {
            return LEFT;
        } else if (keyHandler.rightPressed) {
            return RIGHT;
        } else {
            return direction;
        }
    }

    public void initializePlayer () {
        worldX = Constants.TILE_SIZE * 21;
        worldY = Constants.TILE_SIZE * 21;
        speed = 8;
        direction = DOWN;
    }

    public void update() {
        if (keyHandler.upPressed || keyHandler.downPressed
                || keyHandler.leftPressed || keyHandler.rightPressed) {
            direction = getDirection();
            // CHECK TILE COLLISION TODO create collision switch
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);
            // CHECK OBJECT COLLISION
            int objectIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObjectOf(objectIndex);
            //IF FALSE, PLAYER CAN MOVE
            if (!collisionOn) {
                switch (direction) {
                    case UP -> worldY -= speed;
                    case DOWN -> worldY += speed;
                    case LEFT -> worldX -= speed;
                    case RIGHT -> worldX += speed;
                }
            }
            loadSprites();
            animate();
        }
    }
    public void pickUpObjectOf(int index) {
        if (index != 999) {
            String objectName = gamePanel.obj[index].name;


            switch (objectName) {
                case "Key" -> {
                    gamePanel.playSFX(3);
                    hasKey++;
                    gamePanel.obj[index] = null;
                    gamePanel.ui.showMessage("Key!");
                }
                case "Door" -> {
                if (hasKey > 0) {
                    gamePanel.obj[index] = null;
                    hasKey--;
                } else {
                    gamePanel.ui.showMessage("You need a key!");
                }
                }
                case "Boots" -> {
                    gamePanel.ui.showMessage("Speed Up!");
                    gamePanel.obj[index] = null;
                    speed += 4;
                    gamePanel.playSFX(2);
                }
                case "Chest" -> {
                    gamePanel.ui.gameFinished = true;
                    gamePanel.stopMusic();
                    gamePanel.playMusic(1);
                }
            }
        }

    }
    public void draw(Graphics2D graphics2D) {
        BufferedImage sprite = sprites[spriteIndex];
        graphics2D.drawImage(sprite, screenX, screenY, Constants.TILE_SIZE, Constants.TILE_SIZE, null );
    }
    private void animate() {
        // Increment animation counter
        animationCounter++;
        if (animationCounter >= animationDelay) {
            // Reset animation counter
            animationCounter = 0;
            // Move to the next sprite
            spriteIndex = (spriteIndex + 1) % sprites.length;
        }
    }
}
