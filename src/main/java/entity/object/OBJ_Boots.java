package entity.object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OBJ_Boots extends SuperObject {
    public OBJ_Boots() {
        setName("Boots");
        try {
            setImage(ImageIO.read(new File("src/main/java/res/objects/boots-1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setCollision(true);

    }
}
