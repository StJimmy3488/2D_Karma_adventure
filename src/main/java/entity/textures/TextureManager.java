package entity.textures;

import entity.core.GamePanel;
import entity.core.constants.Constants;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextureManager {
    public final List<Textures> textures;
    public final int[][] mapTextureNum;
    private final GamePanel gamePanel;
    private final String TEXTURE_FOLDER_PATH = "src/main/java/res/textures";
    private final String MAP_PATH = "src/main/java/res/maps/map_2.txt";

    List<String> solid = Arrays.asList("water", "tree", "wall");


    public TextureManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        this.textures = loadTextures();
        this.mapTextureNum = loadMap();
    }

    private int[][] loadMap() {
        int[][] map = new int[Constants.MAX_WORLD_COLUMN][Constants.MAX_WORLD_ROW];
        try (InputStream inputStream = new FileInputStream(MAP_PATH)) {
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < Constants.MAX_WORLD_ROW) {
                String[] numbers = line.split("\t");
                for (int col = 0; col < Constants.MAX_WORLD_COLUMN || col < numbers.length; col++) {
                    map[col][row] = Integer.parseInt(numbers[col]);
                }
                row++;
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load map", e);
        }
        return map;
    }

    private List<Textures> loadTextures() {
        List<Textures> texturesList = new ArrayList<>();
        Path textureFolder = Paths.get(TEXTURE_FOLDER_PATH);
        try (var stream = Files.walk(textureFolder)) {
            stream.sorted()
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".png"))
                    .forEach(path -> {
                        try (InputStream inputStream = Files.newInputStream(path)) {
                            Textures textures = new Textures();
                            String pathString = path.toString();
                            if (solid.stream().anyMatch(pathString::contains)) {
                                textures.collision = true;
                            }
                            textures.image = ImageIO.read(inputStream);
                            texturesList.add(textures);
                        } catch (IOException e) {
                            throw new UncheckedIOException("Failed to load texture: " + path, e);
                        }
                    });
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to traverse texture folder located at: "
                    + textureFolder, e);
        }

        return texturesList;
    }

    public void draw(Graphics2D graphics) {
        int worldColumn;
        int worldRow;

        for (worldRow = 0; worldRow < Constants.MAX_WORLD_ROW; worldRow++) {
            for (worldColumn = 0; worldColumn < Constants.MAX_WORLD_COLUMN; worldColumn++) {
                int textureNum = mapTextureNum[worldColumn][worldRow];

                int worldX = worldColumn * Constants.TILE_SIZE;
                int worldY = worldRow * Constants.TILE_SIZE;
                int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
                int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

                if (worldX + Constants.TILE_SIZE > gamePanel.player.worldX - gamePanel.player.screenX &&
                        worldX - Constants.TILE_SIZE < gamePanel.player.worldX + gamePanel.player.screenX &&
                        worldY + Constants.TILE_SIZE > gamePanel.player.worldY - gamePanel.player.screenY &&
                        worldY - Constants.TILE_SIZE < gamePanel.player.worldY + gamePanel.player.screenY) {

                    graphics.drawImage(textures.get(textureNum).image, screenX, screenY, Constants.TILE_SIZE, Constants.TILE_SIZE, null);
                }
            }
        }
    }
}
