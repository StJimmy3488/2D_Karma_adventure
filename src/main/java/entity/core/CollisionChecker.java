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

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

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
    public int checkObject(Player player, boolean isPlayer) {
        int index = 999;

        for (int i = 0; i < gamePanel.obj.size(); i++) {
            if (gamePanel.obj.get(i) != null) {
                player.getPlayerSolidArea().x = player.worldX + player.getPlayerSolidArea().x;
                player.solidArea.y = player.worldY + player.solidArea.y;

                gamePanel.obj.get(i).objectSolidArea.x = gamePanel.obj.get(i).getWorldX() + gamePanel.obj.get(i).objectSolidArea.x;
                gamePanel.obj.get(i).objectSolidArea.y = gamePanel.obj.get(i).getWorldY() + gamePanel.obj.get(i).objectSolidArea.y;

                switch (player.direction) {
                    case UP -> {
                        player.solidArea.y -= player.getSpeed();
                        if (player.solidArea.intersects(gamePanel.obj.get(i).objectSolidArea)) {
                            if (gamePanel.obj.get(i).hasCollision()) {
                                player.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                    }
                    case DOWN -> {
                        player.solidArea.y += player.getSpeed();
                        if (player.solidArea.intersects(gamePanel.obj.get(i).objectSolidArea)) {
                            if (gamePanel.obj.get(i).hasCollision()) {
                                player.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                    }
                    case LEFT -> {
                        player.solidArea.x -= player.getSpeed();
                        if (player.solidArea.intersects(gamePanel.obj.get(i).objectSolidArea)) {
                            if (gamePanel.obj.get(i).hasCollision()) {
                                player.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                    }
                    case RIGHT -> {
                        player.solidArea.x += player.getSpeed();
                        if (player.solidArea.intersects(gamePanel.obj.get(i).getObjectSolidArea())) {
                            if (gamePanel.obj.get(i).hasCollision()) {
                                player.collisionOn = true;
                            }
                            if (isPlayer) {
                                index = i;
                            }
                        }
                    }
                }
                    player.solidArea.x = player.solidAreaDefaultX;
                    player.solidArea.y = player.solidAreaDefaultY;
                    gamePanel.obj.get(i).getObjectSolidArea().x = gamePanel.obj.get(i).getSolidAreaDefaultX();
                    gamePanel.obj.get(i).getObjectSolidArea().y = gamePanel.obj.get(i).getSolidAreaDefaultY();

            }

        }
        return index;
    }
}
