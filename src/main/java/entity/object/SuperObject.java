package entity.object;

import entity.core.constants.Constants;
import entity.core.GamePanel;
import lombok.*;

import java.awt.*;
import java.awt.image.BufferedImage;

@Data
public class SuperObject {

    public BufferedImage image;

    public String name;

    public boolean hasCollision() {
        return collision;
    }
    private boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48,48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public void draw(Graphics2D graphics, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (((worldX + Constants.TILE_SIZE) > (gamePanel.player.worldX - gamePanel.player.screenX)) &&
                ((worldX - Constants.TILE_SIZE) < (gamePanel.player.worldX + gamePanel.player.screenX)) &&
                ((worldY + Constants.TILE_SIZE) > (gamePanel.player.worldY - gamePanel.player.screenY)) &&
                ((worldY - Constants.TILE_SIZE) < (gamePanel.player.worldY + gamePanel.player.screenY))) {

            graphics.drawImage(image, screenX, screenY, Constants.TILE_SIZE, Constants.TILE_SIZE, null);
        }
    }

}
