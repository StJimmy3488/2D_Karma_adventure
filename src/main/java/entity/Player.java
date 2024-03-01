package entity;

import entity.core.GamePanel;
import entity.core.KeyHandler;
import entity.core.constants.Direction;
import entity.object.interaction.ObjectInteraction;
import entity.object.interaction.ObjectInteractionFactory;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

import static entity.core.constants.Constants.*;
import static entity.core.constants.Direction.*;


public class Player extends Entity{
    @Getter
    GamePanel gamePanel;
    KeyHandler keyHandler;

    private BufferedImage[] sprites;
    private int spriteIndex = 0;
    private int animationCounter = 0;


    @Getter
    @Setter
    private int playerKeyCount = 0;
    private final ObjectInteractionFactory interactionFactory = new ObjectInteractionFactory();

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        initializePlayer();
        solidAreaDefaultX = getSolidArea().x;
        solidAreaDefaultY = getSolidArea().y;

        sprites = direction.getSprites();
    }

    public void initializePlayer () {
        setPlayerSolidArea(new Rectangle(8, 16, 32, 32));
        worldX = PLAYER_START_POSITION_X;
        worldY = PLAYER_START_POSITION_Y;
        speed = 12;
        direction = DOWN;
    }

    public void pickUpObjectOf(int index) {
        if (index != Short.MAX_VALUE && gamePanel.getObjectList().get(index) != null) {
            String objectName = (gamePanel.getObjectList().get(index).getName());
            ObjectInteraction interaction = interactionFactory.getInteraction(objectName);
            if (interaction != null) {
                interaction.interact(this, index);
            }
        }
    }
    private void loadSprites() {
        BufferedImage[] sprites = direction.getSprites();
        if (sprites != null && sprites.length > 0) {
            this.sprites = sprites;
        } else {
            throw new IllegalArgumentException("No sprites found for direction: " + direction);
        }
    }

    public Direction getDirection() {
        if (keyHandler.isUpPressed()) {
            return UP;
        } else if (keyHandler.isDownPressed()) {
            return DOWN;
        } else if (keyHandler.isLeftPressed()) {
            return LEFT;
        } else if (keyHandler.isRightPressed()) {
            return RIGHT;
        } else {
            return direction;
        }
    }


    public void update() {
        if (keyHandler.isPlayerMoving()) {
            direction = getDirection();
            // CHECK TILE COLLISION
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);
            // CHECK OBJECT COLLISION
            int objectIndex = gamePanel.collisionChecker.checkObject(this);
            pickUpObjectOf(objectIndex);
            loadSprites();
            animate();
        }
    }

    public void draw(Graphics2D graphics2D) {
        BufferedImage sprite = sprites[spriteIndex];
        graphics2D.drawImage(sprite, PLAYER_CAMERA_POSITION_X, PLAYER_CAMERA_POSITION_Y, TILE_SIZE, TILE_SIZE, null );
    }

    public void checkKey(int index) {
        if (playerKeyCount > 0) {
            gamePanel.getObjectList().set(index, null);
            playerKeyCount--;
        } else {
            gamePanel.ui.showMessage("You need a key!");
        }
    }

    private void animate() {
        animationCounter++;
        if (animationCounter >= PLAYER_ANIMATION_DELAY) {
            animationCounter = 0;
            spriteIndex = (spriteIndex + 1) % sprites.length;
        }
    }

    public void incrementPlayerKeyCount() {
        playerKeyCount++;
    }

    public void incrementSpeed(int speedPoints) {
        speed += speedPoints;
    }

    public void setPlayerSolidAreaCoords(int x, int y) {
        this.solidArea = new Rectangle(x, y, HALF_TILE_SIZE, HALF_TILE_SIZE);
    }
    public void setPlayerSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }
}
