package entity.object;

import entity.core.GamePanel;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.awt.image.BufferedImage;

import static entity.core.constants.Constants.*;

@Getter
@Setter
public class SuperObject {

    public void setObjectSolidAreaCoords(int x, int y) {
        this.objectSolidArea = new Rectangle(x, y, TILE_SIZE, TILE_SIZE);
    }

    public Rectangle objectSolidArea;
    private BufferedImage image;
    private String name;
    private int worldX;
    private int worldY;
    private int solidAreaDefaultX = 0;
    private int solidAreaDefaultY = 0;
    private boolean collision = false;

    private boolean isObjectInView(GamePanel gamePanel) {
        return (worldX + TILE_SIZE) > (gamePanel.player.worldX - PLAYER_CAMERA_POSITION_X) &&
                (worldX - TILE_SIZE) < (gamePanel.player.worldX + PLAYER_CAMERA_POSITION_X) &&
                (worldY + TILE_SIZE) > (gamePanel.player.worldY - PLAYER_CAMERA_POSITION_Y) &&
                (worldY - TILE_SIZE) < (gamePanel.player.worldY + PLAYER_CAMERA_POSITION_Y);
    }

    public void draw(Graphics2D graphics, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.player.worldX + PLAYER_CAMERA_POSITION_X;
        int screenY = worldY - gamePanel.player.worldY + PLAYER_CAMERA_POSITION_Y;

        if (isObjectInView(gamePanel)) {
            graphics.drawImage(image, screenX, screenY, TILE_SIZE, TILE_SIZE, null);
        }
    }

    public boolean hasCollision() {
        return collision;
    }

    public void setWorldCoords(int worldX, int worldY) {
        this.worldX = worldX * TILE_SIZE;
        this.worldY = worldY * TILE_SIZE;
    }

}
