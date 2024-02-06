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

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues () {
        x = 100;
        y = 100;
        speed = 4;
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
            e.printStackTrace();
        }
    }
    public void update() {
        if (keyHandler.upPressed || keyHandler.downPressed
                || keyHandler.leftPressed || keyHandler.rightPressed) {
            if (keyHandler.upPressed) {
                direction = "up";
                y -= speed;
            } else if (keyHandler.downPressed) {
                direction = "down";
                y += speed;
            } else if (keyHandler.leftPressed) {
                direction = "left";
                x -= speed;
            } else if (keyHandler.rightPressed) {
                direction = "right";
                x += speed;
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
    public void draw(Graphics2D graphics2D) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                if(spriteNum == 3) {
                    image = up3;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                if(spriteNum == 3) {
                    image = down3;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                image = left1;
                }
                if(spriteNum == 2) {
                image = left2;
                }
                if(spriteNum == 3) {
                image = left3;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                if(spriteNum == 3) {
                    image = right3;
                }
                break;
        }
        graphics2D.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null );
    }
}
