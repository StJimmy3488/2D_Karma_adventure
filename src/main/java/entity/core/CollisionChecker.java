package entity.core;

import entity.Entity;

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

        int entityLeftColumn = entityLeftWorldX / gamePanel.tileSize;
        int entityRightColumn = entityLeftWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1, tileNum2;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.textureManager.mapTextureNum[entityRightColumn][entityTopRow];
                if (gamePanel.textureManager.textures.get(tileNum1).collision ||
                        gamePanel.textureManager.textures.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityBottomRow];
                tileNum2 = gamePanel.textureManager.mapTextureNum[entityRightColumn][entityBottomRow];
                if (gamePanel.textureManager.textures.get(tileNum1).collision ||
                        gamePanel.textureManager.textures.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftColumn = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                tileNum1 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityTopRow];
                tileNum2 = gamePanel.textureManager.mapTextureNum[entityLeftColumn][entityBottomRow];
                if (gamePanel.textureManager.textures.get(tileNum1).collision ||
                        gamePanel.textureManager.textures.get(tileNum2).collision) {
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightColumn = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
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
                    case "up" -> {
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
                    case "down" -> {
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
                    case "left" -> {
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
                    case "right" -> {
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
