package entity.object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OBJ_Door extends SuperObject {
    public OBJ_Door() {
        setName("Door");
        try {
            setImage(ImageIO.read(new File("src/main/java/res/objects/door.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setCollision(true);
    }
}
