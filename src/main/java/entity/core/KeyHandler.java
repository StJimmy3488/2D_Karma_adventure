package entity.core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        updateDirectionPressed(code, true);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        updateDirectionPressed(code, false);
    }

    private void updateDirectionPressed(int code, boolean pressed) {
        switch (code) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> upPressed = pressed;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> downPressed = pressed;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> rightPressed = pressed;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> leftPressed = pressed;
        }
    }
}