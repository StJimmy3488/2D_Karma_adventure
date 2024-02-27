package entity.object.interaction;
import entity.Player;
import entity.core.constants.Sounds;

public class KeyInteraction implements ObjectInteraction {
    @Override
    public void interact(Player player, int index) {
        player.getGamePanel().playSFX(Sounds.MARIO_COIN);
        player.incrementPlayerKeyCount();
        player.getGamePanel().obj.set(index, null);
        player.getGamePanel().ui.showMessage("Key!");
    }
}
