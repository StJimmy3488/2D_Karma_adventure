package entity.core;

import entity.Entity;
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
                entityTopRow = (entityTopWorldY - entity.speed) / Constants.TILE_SIZE;
                tileNum1 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.textureManager.mapTextureNum[entityRightColumn][entityTopRow];
                if (gamePanel.textureManager.textures.get(tileNum1).collision ||
                        gamePanel.textureManager.textures.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
            case DOWN:
                entityBottomRow = (entityBottomWorldY + entity.speed) / Constants.TILE_SIZE;
                tileNum1 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityBottomRow];
                tileNum2 = gamePanel.textureManager.mapTextureNum[entityRightColumn][entityBottomRow];
                if (gamePanel.textureManager.textures.get(tileNum1).collision ||
                        gamePanel.textureManager.textures.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
            case LEFT:
                entityLeftColumn = (entityLeftWorldX - entity.speed) / Constants.TILE_SIZE;
                tileNum1 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityBottomRow];
                if (gamePanel.textureManager.textures.get(tileNum1).collision ||
                        gamePanel.textureManager.textures.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
            case RIGHT:
                entityRightColumn = (entityRightWorldX + entity.speed) / Constants.TILE_SIZE;
                tileNum1 = gamePanel.textureManager.mapTextureNum[entityRightColumn][entityTopRow];
                tileNum2 = gamePanel.textureManager.mapTextureNum[entityRightColumn][entityBottomRow];
                if (gamePanel.textureManager.textures.get(tileNum1).collision ||
                        gamePanel.textureManager.textures.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
        }
    }
    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gamePanel.obj.length; i++) {
            if (gamePanel.obj[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                gamePanel.obj[i].solidArea.x = gamePanel.obj[i].worldX + gamePanel.obj[i].solidArea.x;
                gamePanel.obj[i].solidArea.y = gamePanel.obj[i].worldY + gamePanel.obj[i].solidArea.y;

                switch (entity.direction) {
                    case UP -> {
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if (gamePanel.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case DOWN -> {
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if (gamePanel.obj[i].collision) {
                                System.out.println("down");
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case LEFT -> {
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if (gamePanel.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                    case RIGHT -> {
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(gamePanel.obj[i].solidArea)) {
                            if (gamePanel.obj[i].collision) {
                                entity.collisionOn = true;
                            }
                            if (player) {
                                index = i;
                            }
                        }
                    }
                }
                    entity.solidArea.x = entity.solidAreaDefaultX;
                    entity.solidArea.y = entity.solidAreaDefaultY;
                    gamePanel.obj[i].solidArea.x = gamePanel.obj[i].solidAreaDefaultX;
                    gamePanel.obj[i].solidArea.y = gamePanel.obj[i].solidAreaDefaultY;

            }

        }
        return index;
    }
}
