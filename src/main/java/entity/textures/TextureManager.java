package entity.textures;

import entity.core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextureManager {
   private final GamePanel gamePanel;
   private final List<Texture> textures;
   private final int[][] mapTextureNum;



    public TextureManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.textures = loadTextures();
        this.mapTextureNum = loadMap();
    }

    private int[][] loadMap() {
        int[][] map = new int[gamePanel.maxScreenColumn][gamePanel.maxScreenRow];
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getResourceAsStream("/maps/map_1.txt"))))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < gamePanel.maxScreenRow) {
                String[] numbers = line.split(" ");
                for (int col = 0; col < gamePanel.maxScreenColumn && col < numbers.length; col++) {
                    map[col][row] = Integer.parseInt(numbers[col]);
                }
                row++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load map", e);
        }
        return map;
    }
    private List<Texture> loadTextures() {
        List<Texture> textures = new ArrayList<>();
        try {
            for (int i = 1; i < 14; i++) {
                Texture texture = new Texture();
                texture.image = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(
                        "textures/texture_" + i + ".png")));
                textures.add(texture);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load textures", e);
        }
        return textures;
    }
    public void draw(Graphics2D graphics) {
        int x = 0;
        int y = 0;
        for (int row = 0; row < gamePanel.maxScreenRow; row++) {
            for (int col = 0; col < gamePanel.maxScreenColumn; col++) {
                int textureNum = mapTextureNum[col][row];
                graphics.drawImage(textures.get(textureNum).image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
                x += gamePanel.tileSize;
            }
            x = 0;
            y += gamePanel.tileSize;
        }
    }

}
