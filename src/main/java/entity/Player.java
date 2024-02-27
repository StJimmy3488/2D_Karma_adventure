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
        setPlayerSolidArea(new Rectangle(8, 8, 24, 24));
        worldX = PLAYER_START_POSITION_X;
        worldY = PLAYER_START_POSITION_Y;
        speed = 8;
        direction = DOWN;
    }

    public void pickUpObjectOf(int index) {
        if (index != 999 && gamePanel.obj.get(index) != null) {
            String objectName = (gamePanel.obj.get(index).getName());
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
//    public void pickUpObjectOf(int index) {
//        if (index != 999) {
//            String objectName = gamePanel.obj[index].getName();
//
//            switch (objectName) {
//                case "Key" -> {
//                    gamePanel.playSFX(Sounds.MARIO_COIN);
//                    playerKeyCount++;
//                    gamePanel.obj[index] = null;
//                    gamePanel.ui.showMessage("Key!");
//                }
//                case "Door" -> {
//                    checkKey(index);
//
//                }
//                case "Boots" -> {
//                    gamePanel.ui.showMessage("Speed Up!");
//                    gamePanel.obj[index] = null;
//                    speed+=4;
//                    gamePanel.playSFX(Sounds.MARIO_COIN);
//                }
//                case "Chest" -> {
//                    gamePanel.ui.gameFinished = true;
//                    gamePanel.stopMusic();
//                    gamePanel.playMusic(Sounds.LEVEL_UP);
//                }
//            }
//        }
//
//    }


    public void draw(Graphics2D graphics2D) {
        BufferedImage sprite = sprites[spriteIndex];
        graphics2D.drawImage(sprite, PLAYER_CAMERA_POSITION_X, PLAYER_CAMERA_POSITION_Y, TILE_SIZE, TILE_SIZE, null );
    }

    public void checkKey(int index) {
        if (playerKeyCount > 0) {
            gamePanel.obj.set(index, null);
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

    public Rectangle getPlayerSolidArea() {
        return solidArea;
    }
    public void setPlayerSolidArea(Rectangle solidArea) {
        this.solidArea = solidArea;
    }
}
