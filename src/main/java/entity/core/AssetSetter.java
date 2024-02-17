package entity.core;

import entity.object.OBJ_Boots;
import entity.object.OBJ_Chest;
import entity.object.OBJ_Door;
import entity.object.OBJ_Key;

public class AssetSetter {
    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public void setObject() {
        gamePanel.obj[0] = new OBJ_Key();
        gamePanel.obj[0].worldX = 9 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 35 * gamePanel.tileSize;
        gamePanel.obj[7] = new OBJ_Key();
        gamePanel.obj[7].worldX = 34 * gamePanel.tileSize;
        gamePanel.obj[7].worldY = 18 * gamePanel.tileSize;

        gamePanel.obj[1] = new OBJ_Key();
        gamePanel.obj[1].worldX = 8 * gamePanel.tileSize;
        gamePanel.obj[1].worldY = 41 * gamePanel.tileSize;

        gamePanel.obj[2] = new OBJ_Door();
        gamePanel.obj[2].worldX = 8 * gamePanel.tileSize;
        gamePanel.obj[2].worldY = 38 * gamePanel.tileSize;
        gamePanel.obj[4] = new OBJ_Door();
        gamePanel.obj[4].worldX = 9 * gamePanel.tileSize;
        gamePanel.obj[4].worldY = 38 * gamePanel.tileSize;
//        gamePanel.obj[6] = new OBJ_Door();
//        gamePanel.obj[6].worldX = 21 * gamePanel.tileSize;
//        gamePanel.obj[6].worldY = 29 * gamePanel.tileSize;

        gamePanel.obj[3] = new OBJ_Chest();
        gamePanel.obj[3].worldX = 7 * gamePanel.tileSize;
        gamePanel.obj[3].worldY = 35 * gamePanel.tileSize;

        gamePanel.obj[5] = new OBJ_Boots();
        gamePanel.obj[5].worldX = 47 * gamePanel.tileSize;
        gamePanel.obj[5].worldY = 22 * gamePanel.tileSize;


    }
}
