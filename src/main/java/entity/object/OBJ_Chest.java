package entity.object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OBJ_Chest extends SuperObject{
    public OBJ_Chest() {
        setName("Chest");
        try {
            setImage(ImageIO.read(new File("src/main/java/res/objects/chest-1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setCollision(true);

    }
}
