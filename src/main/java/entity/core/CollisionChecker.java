package entity.core;

import entity.Entity;
import entity.Player;
import entity.core.constants.Constants;

public class CollisionChecker {
    GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.getSolidArea().x;
        int entityRightWorldX = entity.worldX + entity.getSolidArea().x + entity.getSolidArea().width;
        int entityTopWorldY = entity.worldY + entity.getSolidArea().y;
        int entityBottomWorldY = entity.worldY + entity.getSolidArea().y + entity.getSolidArea().height;

        int entityLeftColumn = entityLeftWorldX / Constants.TILE_SIZE;
        int entityRightColumn = entityLeftWorldX / Constants.TILE_SIZE;
        int entityTopRow = entityTopWorldY / Constants.TILE_SIZE;
        int entityBottomRow = entityBottomWorldY / Constants.TILE_SIZE;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case UP:
                entityTopRow = (entityTopWorldY - entity.getSpeed()) / Constants.TILE_SIZE;
                tileNum1 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.textureManager.mapTextureNum[entityRightColumn][entityTopRow];
                if (gamePanel.textureManager.textures.get(tileNum1).collision ||
                        gamePanel.textureManager.textures.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
            case DOWN:
                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / Constants.TILE_SIZE;
                tileNum1 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityBottomRow];
                tileNum2 = gamePanel.textureManager.mapTextureNum[entityRightColumn][entityBottomRow];
                if (gamePanel.textureManager.textures.get(tileNum1).collision ||
                        gamePanel.textureManager.textures.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
            case LEFT:
                entityLeftColumn = (entityLeftWorldX - entity.getSpeed()) / Constants.TILE_SIZE;
                tileNum1 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityBottomRow];
                if (gamePanel.textureManager.textures.get(tileNum1).collision ||
                        gamePanel.textureManager.textures.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
            case RIGHT:
                entityRightColumn = (entityRightWorldX + entity.getSpeed()) / Constants.TILE_SIZE;
                tileNum1 = gamePanel.textureManager.mapTextureNum[entityRightColumn][entityTopRow];
                tileNum2 = gamePanel.textureManager.mapTextureNum[entityRightColumn][entityBottomRow];
                if (gamePanel.textureManager.textures.get(tileNum1).collision ||
                        gamePanel.textureManager.textures.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
    public int checkObject(Player player) {
        int index = 999;


        for (int i = 0; i < gamePanel.getObjectList().size(); i++) {
            if (gamePanel.getObjectWith(i) != null) {
                player.setPlayerSolidAreaCoords(player.worldX, player.worldY);
                gamePanel.getObjectWith(i).setObjectSolidAreaCoords(gamePanel.getObjectWith(i).getWorldX(),
                        gamePanel.getObjectWith(i).getWorldY());

                switch (player.getDirection()) {
                    case UP -> {
                        player.solidArea.y -= player.getSpeed();
                        if (player.solidArea.intersects(gamePanel.getObjectWith(i).objectSolidArea)) {
                            if (gamePanel.getObjectWith(i).hasCollision()) {
                                player.collisionOn = true;
                            }
                                index = i;
                        }
                    }
                    case DOWN -> {
                        player.solidArea.y += player.getSpeed();
                        if (player.solidArea.intersects(gamePanel.getObjectWith(i).objectSolidArea)) {
                            if (gamePanel.getObjectWith(i).hasCollision()) {
                                player.collisionOn = true;
                            }
                                index = i;
                        }
                    }
                    case LEFT -> {
                        player.solidArea.x -= player.getSpeed();
                        if (player.solidArea.intersects(gamePanel.getObjectWith(i).objectSolidArea)) {
                            if (gamePanel.getObjectWith(i).hasCollision()) {
                                player.collisionOn = true;
                            }
                                index = i;
                        }
                    }
                    case RIGHT -> {
                        player.solidArea.x += player.getSpeed();
                        if (player.solidArea.intersects(gamePanel.getObjectWith(i).getObjectSolidArea())) {
                            if (gamePanel.getObjectWith(i).hasCollision()) {
                                player.collisionOn = true;
                            }
                                index = i;
                        }
                    }
                }
                    player.solidArea.x = player.solidAreaDefaultX;
                    player.solidArea.y = player.solidAreaDefaultY;
                    gamePanel.getObjectWith(i).getObjectSolidArea().x = gamePanel.getObjectWith(i).getSolidAreaDefaultX();
                    gamePanel.getObjectWith(i).getObjectSolidArea().y = gamePanel.getObjectWith(i).getSolidAreaDefaultY();

            }

        }
        return index;
    }
}
