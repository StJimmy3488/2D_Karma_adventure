package entity.object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OBJ_Key extends SuperObject{

    public OBJ_Key() {
        setName("Key");
        try {
            image = (ImageIO.read(new File("src/main/java/res/objects/key-1.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setCollision(true);


    }
}
