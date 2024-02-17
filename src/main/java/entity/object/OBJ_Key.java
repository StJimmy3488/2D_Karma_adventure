package entity.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Key extends SuperObject{
    public OBJ_Key() {
        name = "Key";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/key-1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        collision = true;

    }
}
