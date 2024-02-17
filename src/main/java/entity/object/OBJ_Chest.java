package entity.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
    public OBJ_Chest() {
        name = "Chest";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/chest-2.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        collision = true;

    }
}
