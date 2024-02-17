package entity;

import entity.core.GamePanel;
import entity.core.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenHeight/2 - (gamePanel.tileSize/2);
        solidArea = new Rectangle(8, 8, 24, 24);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues () {
        worldX = gamePanel.tileSize * 21;
        worldY = gamePanel.tileSize * 21;
        speed = 12;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/karma_cat_up_1.png"));
            up2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/karma_cat_up_3.png"));
            up3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/karma_cat_up_2.png"));
            down1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/karma_cat_down_1.png"));
            down2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/karma_cat_down_2.png"));
            down3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/karma_cat_down_3.png"));
            left1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/karma_cat_left_1.png"));
            left2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/karma_cat_left_2.png"));
            left3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/karma_cat_left_3.png"));
            right1 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/karma_cat_right_1.png"));
            right2 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/karma_cat_right_2.png"));
            right3 = ImageIO.read(getClass().getClassLoader().getResourceAsStream("player/karma_cat_right_3.png"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load player image", e);
        }
    }
    public void update() {
        if (keyHandler.upPressed || keyHandler.downPressed
                || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
//                worldY -= speed;
            } else if (keyHandler.downPressed) {
                direction = "down";
//                worldY += speed;
            } else if (keyHandler.leftPressed) {
                direction = "left";
//                worldX -= speed;
            } else if (keyHandler.rightPressed) {
                direction = "right";
//                worldX += speed;
            }
            // CHECK TILE COLLISION TODO create collision switch
            collisionOn = false;
            gamePanel.collisionChecker.checkTile(this);
            // CHECK OBJECT COLLISION
            int objectIndex = gamePanel.collisionChecker.checkObject(this, true);
            pickUpObjectOf(objectIndex);
            //IF FALSE, PLAYER CAN MOVE
            if (!collisionOn) {
                switch (direction) {
                    case "up" -> worldY -= speed;
                    case "down" -> worldY += speed;
                    case "left" -> worldX -= speed;
                    case "right" -> worldX += speed;
                }
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 3;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                } else if (spriteNum == 3) {
                    spriteNum = 2;
                }
                spriteCounter = 0;
            }
        }
    }
    public void pickUpObjectOf(int index) {
        if (index != 999) {
            String objectName = gamePanel.obj[index].name;
            if (objectName.equals("Key")) {
                gamePanel.obj[index] = null;
                hasKey++;
                System.out.println("Key: " + hasKey);
            } else if (objectName.equals("Door")) {
                if (hasKey > 0) {
                    gamePanel.obj[index] = null;
                    hasKey--;
                }
                System.out.println("Key: " + hasKey);
            }
            else if (objectName.equals("Boots")) {
                speed += 4;
                gamePanel.obj[index]=null;
            }
        }

    }
    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;
        switch (direction) {
            case "up" -> {
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                if (spriteNum == 3) {
                    image = up3;
                }
            }
            case "down" -> {
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                if (spriteNum == 3) {
                    image = down3;
                }
            }
            case "left" -> {
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                if (spriteNum == 3) {
                    image = left3;
                }
            }
            case "right" -> {
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
                }
                if (spriteNum == 3) {
                    image = right3;
                }
            }
        }
        graphics2D.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null );
    }
}
