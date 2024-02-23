package entity.core;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResourceLoader {

    private String resourceType;
    public void selectResourceLoader(String resourceType, String path, String searchCriteria) {
        switch (resourceType) {
        case ("sfx") -> loadSFX(path, searchCriteria);
        case ("image") -> loadImage(path, searchCriteria);
        }
    }

    private void loadSFX(String path, String searchCriteria) {

    }

    public void loadImage(String path, String searchCriteria) {

        try {
            BufferedImage image = ImageIO.read(new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e); //TODO implement logger
        }
    }
}
