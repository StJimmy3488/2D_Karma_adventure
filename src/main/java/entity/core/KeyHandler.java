package entity.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    boolean offPressed = false;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        int code = e.getKeyCode();

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        soundSwitch(code, offPressed);
        updateDirectionPressed(code, true);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        updateDirectionPressed(code, false);

    }
    private void soundSwitch(int code, boolean pressed) {
        if (code == KeyEvent.VK_M && pressed) {
            gamePanel.playMusic(0);
            offPressed = false;
        }
        if (code == KeyEvent.VK_M && !pressed) {
            gamePanel.stopMusic();
            offPressed = true;
        }
    }

    private void updateDirectionPressed(int code, boolean pressed) {
        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
            upPressed = pressed;
        } else if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
            downPressed = pressed;
        } else if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
            rightPressed = pressed;
        } else if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
            leftPressed = pressed;
        }
    }
}