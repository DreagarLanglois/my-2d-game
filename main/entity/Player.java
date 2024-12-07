package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import handlers.KeyHandler;
import view.GamePanel;

public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp){
        this.gp = gp;
        this.keyH = gp.getKeyHandler();
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);
        setDefaultValues();
        getPlayerImage();
    }

    public void getPlayerImage(){
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/resources/player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/resources/player/boy_right_2.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void setDefaultValues(){
        worldX = gp.tileSize * 9;
        worldY = gp.tileSize * 14;
        speed = 4;
        direction = "down";
    }

    public void update(){
        this.move();
        this.animate();        
    }
    public void draw(Graphics2D g2){
        BufferedImage image = getDirection();        
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

    public void move(){        
        if(keyH.upPressed == true){
            direction = "up";
            worldY -= speed;            
        }else if(keyH.downPressed == true){
            direction = "down";
            worldY += speed;
        }else if(keyH.leftPressed == true){
            direction = "left";
            worldX -= speed;
        }else if(keyH.rightPressed == true){
            direction = "right";
            worldX += speed;
        }       
    }

    public void animate(){
        if(keyH.downPressed == true || keyH.leftPressed == true ||
            keyH.rightPressed == true || keyH.upPressed == true){
                spriteCounter++;
                if(spriteCounter > 13){
                    if(spriteNumber == 1){
                        spriteNumber = 2;
                    }else{
                        spriteNumber = 1;
                    }
                    spriteCounter = 0;
                }
            }
    }

    public BufferedImage getDirection(){
        BufferedImage image = null;
        switch(direction){
            case "up": 
                if(spriteNumber == 1){
                    image = up1;
                }
                if(spriteNumber == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNumber == 1){
                    image = down1;
                }
                if(spriteNumber == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNumber == 1){
                    image = left1;
                }
                if(spriteNumber == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNumber == 1){
                    image = right1;
                }
                if(spriteNumber == 2){
                    image = right2;
                }
                break;
        }
        return image;
    }
}
