package entity.core.constants;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT;
    private final BufferedImage[] sprites;

    Direction() {
        String directionName = name().toLowerCase();
        Path playerSpritePath = Paths.get("src/main/java/res/player");

        try (var stream = Files.walk(playerSpritePath)) {
            sprites = stream
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .filter(fileName -> fileName.toLowerCase().contains(directionName))
                    .map(this::loadSprite)
                    .toArray(BufferedImage[]::new);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to traverse folder located at: "
                    + playerSpritePath , e);
        }

        if (sprites.length == 0) {
            throw new IllegalStateException("No sprites found for direction: " + directionName);
        }
    }

    private BufferedImage loadSprite(String fileName) {
        try {
            return ImageIO.read(new FileInputStream(fileName));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load player image: "+ fileName, e);
        }
    }

    public BufferedImage[] getSprites() {
        return sprites;
    }
}
