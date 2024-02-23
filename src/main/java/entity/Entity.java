package entity;

import entity.core.constants.Direction;
import lombok.Data;

import java.awt.*;

@Data
public class Entity {

    public boolean collisionOn = false;

    public int worldX, worldY;
    public int speed;

    public Direction direction;
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;


}
