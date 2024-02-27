package entity.object.interaction;

import entity.Player;

public class DoorInteraction implements ObjectInteraction {

    @Override
    public void interact(Player player, int index) {
        player.checkKey(index);
    }
}
