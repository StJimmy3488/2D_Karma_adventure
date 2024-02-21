package entity.core;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {


        JCheckBox button = new JCheckBox();
        JFrame window = new JFrame("2D RPG");
        button.setSize(30, 30);

        GamePanel gamePanel = new GamePanel();
        button.addActionListener(e -> {
            if (button.isSelected()) gamePanel.stopMusic();
            else gamePanel.playMusic(0);
        });
        gamePanel.setLayout(new GridBagLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);

        window.add(gamePanel);

        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setupGame();
        gamePanel.startThread();
    }
}

