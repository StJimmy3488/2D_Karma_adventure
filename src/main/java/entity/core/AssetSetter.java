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
        gamePanel.getObjectList().add(0, new OBJ_Key());
        gamePanel.getObjectWith(0).setWorldCoords(9, 35);


        gamePanel.getObjectList().add(1, new OBJ_Key());
        gamePanel.getObjectWith(1).setWorldCoords(15, 10);
        gamePanel.getObjectList().add(2, new OBJ_Key());
        gamePanel.getObjectWith(2).setWorldCoords(16, 11);

        gamePanel.getObjectList().add(3, new OBJ_Chest());
        gamePanel.getObjectWith(3).setWorldCoords(7, 35);

        gamePanel.getObjectList().add(4, new OBJ_Door());
        gamePanel.getObjectWith(4).setWorldCoords(9, 38);


        gamePanel.getObjectList().add(5, new OBJ_Boots());
        gamePanel.getObjectWith(5).setWorldCoords(47, 22);

        gamePanel.getObjectList().add(6, new OBJ_Door());
        gamePanel.getObjectWith(6).setWorldCoords(10, 44);
        gamePanel.getObjectList().add(7, new OBJ_Key());
        gamePanel.getObjectWith(7).setWorldCoords(44, 16);

        gamePanel.getObjectList().add(8, new OBJ_Key());
        gamePanel.getObjectWith(8).setWorldCoords(22, 22);

        gamePanel.getObjectList().add(9, new OBJ_Key());
        gamePanel.getObjectWith(9).setWorldCoords(23, 22);

    }
}
