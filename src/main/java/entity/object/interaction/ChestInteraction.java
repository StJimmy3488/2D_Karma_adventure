package entity.object.interaction;

import entity.Player;
import entity.core.constants.Sounds;

public class ChestInteraction implements ObjectInteraction {
    @Override
    public void interact(Player player, int index) {
        player.getGamePanel().ui.gameFinished = true;
        player.getGamePanel().stopMusic();
        player.getGamePanel().playMusic(Sounds.LEVEL_UP);
    }
}
