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
        gamePanel.obj.add(0, new OBJ_Key());
        gamePanel.obj.get(0).setWorldCoords(9, 35);


        gamePanel.obj.add(1, new OBJ_Key());
        gamePanel.obj.get(1).setWorldCoords(15, 10);
        gamePanel.obj.add(2, new OBJ_Key());
        gamePanel.obj.get(2).setWorldCoords(16, 11);

        gamePanel.obj.add(3, new OBJ_Chest());
        gamePanel.obj.get(3).setWorldCoords(7, 35);

        gamePanel.obj.add(4, new OBJ_Door());
        gamePanel.obj.get(4).setWorldCoords(9, 38);


        gamePanel.obj.add(5, new OBJ_Boots());
        gamePanel.obj.get(5).setWorldCoords(47, 22);

        gamePanel.obj.add(6, new OBJ_Door());
        gamePanel.obj.get(6).setWorldCoords(10, 44);
        gamePanel.obj.add(7, new OBJ_Key());
        gamePanel.obj.get(7).setWorldCoords(44, 16);

    }
}
