package entity.textures;

import entity.core.GamePanel;

public class TextureManager {
    GamePanel gamePanel;
    Texture[] texture;

    public TextureManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        texture = new Texture[10];
    }
}
