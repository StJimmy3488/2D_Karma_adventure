package entity.core;

import entity.core.constants.Constants;
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
        gamePanel.obj[0].worldX = 9 * Constants.TILE_SIZE;
        gamePanel.obj[0].worldY = 35 * Constants.TILE_SIZE;
        gamePanel.obj[7] = new OBJ_Key();
        gamePanel.obj[7].worldX = 44 * Constants.TILE_SIZE;
        gamePanel.obj[7].worldY = 16 * Constants.TILE_SIZE;

        gamePanel.obj[1] = new OBJ_Key();
        gamePanel.obj[1].worldX = 15 * Constants.TILE_SIZE;
        gamePanel.obj[1].worldY = 10 * Constants.TILE_SIZE;


        gamePanel.obj[4] = new OBJ_Door();
        gamePanel.obj[4].worldX = 9 * Constants.TILE_SIZE;
        gamePanel.obj[4].worldY = 38 * Constants.TILE_SIZE;
        gamePanel.obj[6] = new OBJ_Door();
        gamePanel.obj[6].worldX = 10 * Constants.TILE_SIZE;
        gamePanel.obj[6].worldY = 44 * Constants.TILE_SIZE;
//        gamePanel.obj[6] = new OBJ_Door();
//        gamePanel.obj[6].worldX = 21 * Constants.TILE_SIZE;
//        gamePanel.obj[6].worldY = 29 * Constants.TILE_SIZE;

        gamePanel.obj[3] = new OBJ_Chest();
        gamePanel.obj[3].worldX = 7 * Constants.TILE_SIZE;
        gamePanel.obj[3].worldY = 35 * Constants.TILE_SIZE;

        gamePanel.obj[5] = new OBJ_Boots();
        gamePanel.obj[5].worldX = 47 * Constants.TILE_SIZE;
        gamePanel.obj[5].worldY = 22 * Constants.TILE_SIZE;


    }
}
