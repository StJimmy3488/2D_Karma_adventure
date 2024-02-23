package entity.core;

import entity.core.constants.Sounds;
import lombok.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Data
public class KeyHandler implements KeyListener {

    GamePanel gamePanel;

    private boolean upPressed, downPressed, leftPressed, rightPressed;
    private boolean offPressed = false;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        handleSoundSwitch(code, offPressed);
        updateDirectionPressed(code, true);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        updateDirectionPressed(code, false);

    }
    private void handleSoundSwitch(int code, boolean pressed) {
        if (code == KeyEvent.VK_M && pressed) {
            gamePanel.playMusic(Sounds.KARMA_CAT_INTRO);
            offPressed = false;
        }
        if (code == KeyEvent.VK_M && !pressed) {
            gamePanel.stopMusic();
            offPressed = true;
        }
    }

    private void updateDirectionPressed(int code, boolean pressed) {
        switch (code) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> setUpPressed(pressed);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> setDownPressed(pressed);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> setRightPressed(pressed);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> setLeftPressed(pressed);
        }
    }
    public boolean isPlayerMoving() {
        return isUpPressed() || isDownPressed()
                || isLeftPressed() || isRightPressed();
    }
}