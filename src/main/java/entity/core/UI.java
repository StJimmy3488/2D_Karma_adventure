package entity.core;

import entity.core.constants.Constants;
import entity.object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gamePanel;
    Font messageFont, congradsMessageFont;
    BufferedImage keyImage;
    public boolean messageOn = false;
    public String message = "";
    private int messageTime;
    private final int MESSAGE_DURATION = 120;
    public boolean gameFinished = false;



    public void showMessage(String text) {
        message = text;
        messageOn = true;
        messageTime = 0;
    }

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        messageFont = new Font("Consolas", Font.PLAIN, 30);
        congradsMessageFont = new Font("Courier", Font.BOLD, 40);
        OBJ_Key key = new OBJ_Key();
        keyImage = key.getImage();

    }


    public void draw(Graphics2D graphics2D) {

        if(gameFinished) {
            graphics2D.setFont(congradsMessageFont);
            graphics2D.setColor(new Color(79, 32, 32));
            String message = "You found the treasure!";
            int textLength = (int) graphics2D.getFontMetrics().getStringBounds(message, graphics2D).getWidth();
            int x = Constants.SCREEN_WIDTH/2 - textLength/2;
            int y = Constants.SCREEN_HEIGHT/2 - (Constants.TILE_SIZE*3);

            graphics2D.drawString(message, x, y);
            gamePanel.gameThread =null;
        }

        graphics2D.setFont(messageFont);
        graphics2D.setColor(Color.white);
        graphics2D.drawImage(keyImage, Constants.TILE_SIZE/4,Constants.TILE_SIZE/4, Constants.TILE_SIZE, Constants.TILE_SIZE, null);
        graphics2D.drawImage(keyImage, Constants.TILE_SIZE/4,Constants.TILE_SIZE/4, Constants.TILE_SIZE, Constants.TILE_SIZE, null);
        graphics2D.drawString("x " + gamePanel.player.hasKey, 65 , 44);

        drawMessage(graphics2D);

    }

    private void drawMessage(Graphics2D graphics2D) {
        if (messageOn) {
            graphics2D.setFont(messageFont);
            graphics2D.drawString(message, Constants.TILE_SIZE/2, Constants.TILE_SIZE*5);
            messageTime++;
            messageScreenTime();
        }
    }

    private void messageScreenTime() {
        if (messageTime > MESSAGE_DURATION) {
            messageTime = 0;
            messageOn = false;
        }
    }
}
