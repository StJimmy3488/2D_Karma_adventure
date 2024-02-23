package entity.object.interaction;

import entity.Player;
import entity.core.constants.Sounds;
import lombok.Getter;

public interface ObjectInteractionHandler {
    void interact(Player player, int index);
}
