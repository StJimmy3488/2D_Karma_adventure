package entity.core;

import entity.Entity;
import entity.Player;
import entity.core.constants.Constants;
import entity.object.SuperObject;

import java.awt.*;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.getWorldX() + entity.getSolidArea().x;
        int entityRightWorldX = entityLeftWorldX + entity.getSolidArea().width;
        int entityTopWorldY = entity.getWorldY() + entity.getSolidArea().y;
        int entityBottomWorldY = entityTopWorldY + entity.getSolidArea().height;

        int entityLeftColumn = entityLeftWorldX / Constants.TILE_SIZE;
        int entityRightColumn = entityRightWorldX / Constants.TILE_SIZE;
        int entityTopRow = entityTopWorldY / Constants.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / Constants.TILE_SIZE;

        int tileNum1, tileNum2;

        switch (entity.getDirection()) {
            case UP -> {
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / Constants.TILE_SIZE;
                tileNum1 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.textureManager.mapTextureNum[entityRightColumn][entityTopRow];
                if (gamePanel.textureManager.textures.get(tileNum1).collision ||
                        gamePanel.textureManager.textures.get(tileNum2).collision) {
                    entity.collisionOn = true;
                } else {
                    entity.worldY -= entity.speed;
                }
            }
            case DOWN -> {
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / Constants.TILE_SIZE;
                tileNum1 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityBottomRow];
                tileNum2 = gamePanel.textureManager.mapTextureNum[entityRightColumn][entityBottomRow];
                if (gamePanel.textureManager.textures.get(tileNum1).collision ||
                        gamePanel.textureManager.textures.get(tileNum2).collision) {
                    entity.collisionOn = true;
                } else {
                    entity.worldY += entity.speed;
                }
            }
            case LEFT -> {
                entityLeftColumn = (entityLeftWorldX - entity.getSpeed()) / Constants.TILE_SIZE;
                tileNum1 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityBottomRow];
                if (gamePanel.textureManager.textures.get(tileNum1).collision ||
                        gamePanel.textureManager.textures.get(tileNum2).collision) {
                    entity.collisionOn = true;
                } else {
                    entity.worldX -= entity.speed;
                }
            }
            case RIGHT -> {
                entityRightColumn = (entityRightWorldX + entity.getSpeed()) / Constants.TILE_SIZE;
                tileNum1 = gamePanel.textureManager.mapTextureNum[entityRightColumn][entityTopRow];
                tileNum2 = gamePanel.textureManager.mapTextureNum[entityRightColumn][entityBottomRow];
                if (gamePanel.textureManager.textures.get(tileNum1).collision ||
                        gamePanel.textureManager.textures.get(tileNum2).collision) {
                    entity.collisionOn = true;
                } else {
                    entity.worldX += entity.speed;
                }
            }
        }
    }

    public int checkObject(Player player) {
        int index = Short.MAX_VALUE;


        for (int i = 0; i < gamePanel.getObjectList().size(); i++) {
            SuperObject gameObject = gamePanel.getObjectWith(i);
            if (gameObject != null) {
                setSolidAreaCoordsFor(player, gameObject);
                Rectangle playerSolidArea = player.getSolidArea();
                Rectangle objectSolidArea = gameObject.getObjectSolidArea();

                switch (player.getDirection()) {
                    case UP -> {
                        playerSolidArea.y -= player.getSpeed();
                        if (playerSolidArea.intersects(objectSolidArea)) {
                            if (gameObject.hasCollision()) {
                                player.setCollisionOn(true);
                            }
                            index = i;
                        }
                    }
                    case DOWN -> {
                        playerSolidArea.y += player.getSpeed();
                        if (playerSolidArea.intersects(objectSolidArea)) {
                            if (gameObject.hasCollision()) {
                                player.setCollisionOn(true);
                            }
                            index = i;
                        }
                    }
                    case LEFT -> {
                        playerSolidArea.x -= player.getSpeed();
                        if (playerSolidArea.intersects(objectSolidArea)) {
                            if (gameObject.hasCollision()) {
                                player.setCollisionOn(true);
                            }
                            index = i;
                        }
                    }
                    case RIGHT -> {
                        playerSolidArea.x += player.getSpeed();
                        if (playerSolidArea.intersects(objectSolidArea)) {
                            if (gameObject.hasCollision()) {
                                player.setCollisionOn(true);
                            }
                            index = i;
                        }
                    }
                }
                player.setPlayerSolidAreaCoords(player.getSolidAreaDefaultX(),
                        player.getSolidAreaDefaultY());

                gameObject.setObjectSolidAreaCoords(gameObject.getSolidAreaDefaultX(),
                        gameObject.getSolidAreaDefaultY());

            }

        }
        return index;
    }

    void setSolidAreaCoordsFor(Player player, SuperObject gameObject) {
        player.setPlayerSolidAreaCoords(player.worldX, player.worldY);
        gameObject.setObjectSolidAreaCoords(gameObject.getWorldX(),
                gameObject.getWorldY());
    }
}
