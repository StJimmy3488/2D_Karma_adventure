package entity.object.interaction;

import entity.Player;
import entity.core.constants.Sounds;

public class BootsInteraction implements ObjectInteraction {
    @Override
    public void interact(Player player, int index) {
        player.getGamePanel().ui.showMessage("Speed Up!");
        player.getGamePanel().getObjectList().set(index, null);
        player.incrementSpeed(4);
        player.getGamePanel().playSFX(Sounds.MARIO_COIN);
    }

}


