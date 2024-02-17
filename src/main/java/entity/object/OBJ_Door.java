package entity.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door extends SuperObject {
    public OBJ_Door() {
        name = "Door";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/door-1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        collision = true;
    }
}
