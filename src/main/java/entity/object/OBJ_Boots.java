package entity.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Boots extends SuperObject {
    public OBJ_Boots() {
        name = "Boots";
        try {
            image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("objects/boots-1.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        collision = true;

    }
}
